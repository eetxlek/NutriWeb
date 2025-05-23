package nutricion.hexagonal.infra.persistencia.repos.imple;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.dominio.interfaces.DeClases.DespensaRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.DespensaEntity;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;
import nutricion.hexagonal.infra.persistencia.repos.DespensaRepository;
import nutricion.hexagonal.infra.persistencia.repos.ProductoRepository;
import nutricion.hexagonal.infra.persistencia.repos.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class DespensaRepoImple implements DespensaRepoSalida {

    private static final Logger log = LoggerFactory.getLogger(DespensaRepoImple.class);

    private final DespensaRepository jpa;
    private final ProductoRepository projpa;
    private final UsuarioRepository usujpa;

    public DespensaRepoImple(DespensaRepository jpa, ProductoRepository projpa, UsuarioRepository usujpa) {
        this.jpa = jpa;
        this.projpa = projpa;
        this.usujpa = usujpa;
    }

    @Override
    public void guardar(Despensa d) {
        DespensaEntity entity;

        if (d.getId() != null) {
            entity = jpa.findById(d.getId())
                    .orElseThrow(() -> new RuntimeException("Entrada en despensa no encontrada"));
        } else {
            entity = new DespensaEntity();
        }
        ProductoEntity productoEntity = projpa.findById(d.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        UsuarioEntity usuarioEntity = usujpa.findById(d.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        entity.setProducto(productoEntity);
        entity.setIdUsuario(usuarioEntity);
        entity.setCantidad(d.getCantidad());
        entity.setFechaCompra(d.getFechaCompra());

        jpa.save(entity); // Esto es el verdadero save
    }

    @Override
    public Optional<Despensa> findByUsuarioCorreoElectronicoAndProductoId(String correoElectronico,
            Integer idProducto) {
        log.info("➡️ Buscando en JPA: email={}, idProducto={}", correoElectronico, idProducto);
        if (idProducto == null || idProducto == 0) {
            return Optional.empty(); // Si el idProducto es null o 0, no buscamos y devolvemos Optional.empty() y se
                                     // pasará a la lógica para crear una nueva entrada en la despensa.
        }
        DespensaEntity entity = jpa.findByUsuario_CorreoElectronicoAndProducto_Id(correoElectronico, idProducto);

        if (entity == null) {
            return Optional.empty(); // Evita llamar toDomain con null
        }
        return Optional.of(toDomain(entity));
    }

    @Override
    public boolean existe(String correoElectronico, Integer id) {
        return jpa.findByUsuario_CorreoElectronicoAndProducto_Id(correoElectronico, id) != null;
    }

    @Override
    public Optional<Despensa> findById(Integer id) {
        Optional<DespensaEntity> entity = jpa.findById(id);
        return entity.map(this::toDomain); // Usamos map para convertir la entidad a dominio
    }

    @Override
    public Set<Despensa> findByUsuarioCorreoElectronico(String correoElectronico) {
        Set<DespensaEntity> entities = jpa.findByUsuarioCorreoElectronico(correoElectronico);
        return entities.stream().map(this::toDomain).collect(Collectors.toSet()); // Convertir todas las entidades a
                                                                                  // dominio
    }
    
    public DespensaEntity toEntity(Despensa despensa, ProductoEntity productoEntity, UsuarioEntity usuarioEntity) {
        DespensaEntity entity = new DespensaEntity();
        entity.setId(despensa.getId()); // faltaba id de despensa
        entity.setProducto(productoEntity);
        entity.setIdUsuario(usuarioEntity);
        entity.setCantidad(despensa.getCantidad());
        entity.setFechaCompra(despensa.getFechaCompra());
        return entity;
    }

    public Despensa toDomain(DespensaEntity entity) {
        if (entity == null || entity.getProducto() == null) {
            log.error("Error al mapear DespensaEntity: entidad o producto es null");
            return null;
        }
        Producto producto = productotoDomain(entity.getProducto());
        return new Despensa(
                entity.getId(),
                entity.getIdUsuario().getId(), // Obtener el ID del usuario desde la entidad porque para despensa
                                                      // no hace falta usuarioEntity sino su id
                producto,
                entity.getCantidad(),
                entity.getFechaCompra());
    }

    // Entity -> Domain
    public Producto productotoDomain(ProductoEntity entity) {
        Producto p = new Producto();
        p.setId(entity.getId());
        p.setNombre(entity.getNombreProducto());
        return p;
    }


}
