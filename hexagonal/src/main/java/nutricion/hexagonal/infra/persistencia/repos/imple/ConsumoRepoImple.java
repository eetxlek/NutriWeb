package nutricion.hexagonal.infra.persistencia.repos.imple;

import org.springframework.stereotype.Repository;
import nutricion.hexagonal.dominio.clases.Consumo;
import nutricion.hexagonal.dominio.interfaces.DeClases.ConsumoRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.ConsumoEntity;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;
import nutricion.hexagonal.infra.persistencia.repos.ConsumoRepository;
import nutricion.hexagonal.infra.persistencia.repos.ProductoRepository;
import nutricion.hexagonal.infra.persistencia.repos.UsuarioRepository;

@Repository
public class ConsumoRepoImple implements ConsumoRepoSalida {

    private final ConsumoRepository jpa;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuariorepo;

    public ConsumoRepoImple(ConsumoRepository jpa, ProductoRepository productoRepository,
            UsuarioRepository usuariorepo) {
        this.jpa = jpa;
        this.productoRepository = productoRepository;
        this.usuariorepo = usuariorepo;
    }

    @Override
    public void guardar(Consumo consumo) {
        ProductoEntity productoEntity = productoRepository.findById(consumo.getProducto().getId())
                .orElseThrow(
                        () -> new RuntimeException("Producto no encontrado con ID: " + consumo.getProducto().getId()));

        UsuarioEntity usuarioEntity = usuariorepo.findById(consumo.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + consumo.getIdUsuario()));
        ConsumoEntity entity = toEntity(consumo, productoEntity, usuarioEntity);
        jpa.save(entity);
    }

    public static ConsumoEntity toEntity(Consumo consumo, ProductoEntity productoEntity, UsuarioEntity usuarioEntity) {
        ConsumoEntity entity = new ConsumoEntity();
        entity.setProducto(productoEntity);
        entity.setIdUsuario(usuarioEntity);
        entity.setCantidad(consumo.getCantidad());
        entity.setFechaConsumo(consumo.getFechaConsumo());
        return entity;
    }

}
