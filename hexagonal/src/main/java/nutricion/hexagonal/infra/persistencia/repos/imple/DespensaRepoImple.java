package nutricion.hexagonal.infra.persistencia.repos.imple;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.dominio.interfaces.DeClases.DespensaRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.DespensaEntity;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;
import nutricion.hexagonal.infra.persistencia.repos.DespensaRepository;
import nutricion.hexagonal.infra.persistencia.repos.ProductoRepoJpa;
import nutricion.hexagonal.infra.persistencia.repos.UsuarioRepository;

@Repository
public class DespensaRepoImple implements DespensaRepoSalida {

    private final DespensaRepository jpa;
    private final ProductoRepoJpa productoRepository;
    private final UsuarioRepository usuarioRepository;

    public DespensaRepoImple(DespensaRepository jpa, ProductoRepoJpa productoRepository, UsuarioRepository usuarioRepository) {
        this.jpa = jpa;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void guardar(Despensa d) {
        ProductoEntity productoEntity = productoRepository.findById(d.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        
    UsuarioEntity usuarioEntity = usuarioRepository.findById(d.getIdUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));        

            DespensaEntity entity = toEntity(d, productoEntity, usuarioEntity);
        jpa.save(entity);
    }

    @Override
    public Optional<Despensa> findByUsuarioCorreoElectronicoAndProductoId(String email, Integer idProducto) {
        return jpa.findByUsuarioCorreoElectronicoAndProductoId(email, idProducto).map(entity -> toDomain((entity)));  // Convierte la entidad a dominio
    }

    public DespensaEntity toEntity(Despensa despensa, ProductoEntity productoEntity, UsuarioEntity usuarioEntity) {
        DespensaEntity entity = new DespensaEntity();
        entity.setProducto(productoEntity);
        entity.setIdUsuario(usuarioEntity);
        entity.setCantidad(despensa.getCantidad());
        entity.setFechaCompra(despensa.getFechaCompra());
        return entity;
    }
    public Despensa toDomain(DespensaEntity entity) {
        Producto producto = productotoDomain(entity.getProducto());
        return new Despensa(
            entity.getId(),
            entity.getIdUsuario().getIdUsuario(),  // Obtener el ID del usuario desde la entidad
            producto,
            entity.getCantidad(),
            entity.getFechaCompra()
        );
    }
    
    
    // Mapeo Entity -> Domain
    // public Despensa toDomain(DespensaEntity entity) {
    //     Producto producto = productotoDomain(entity.getProducto());  // Convertir ProductoEntity a Producto
    //     return new Despensa(
    //         entity.getId(),
    //         entity.getIdUsuario(),
    //         producto,
    //         entity.getCantidad(),
    //         entity.getFechaCompra()
    //     );
    // }

    // public DespensaEntity toEntity(Despensa despensa, ProductoEntity productoEntity) { //despensaEntity no tiene id en el constructor, solo podría hacer GET si existe
    //     return new DespensaEntity(
    //             productoEntity,
    //             despensa.getIdUsuario() ,
    //             despensa.getCantidad(),
    //             despensa.getFechaCompra()
    //     );
    // }
     // Mapeo Entity -> Domain
     public Producto productotoDomain(ProductoEntity entity) {
        Producto p = new Producto();
        p.setId(entity.getId_producto());
        p.setNombre(entity.getNombreProducto());
        p.setDescripcion(entity.getDescripcion());
        return p;
    }

    @Override
    public boolean existe(String email, Integer id) {
        Optional<DespensaEntity> existente = jpa.findByUsuarioCorreoElectronicoAndProductoId(email, id);
        // Si el Optional no está vacío, significa que ya existe la despensa
        return existente.isPresent();
    }




}
