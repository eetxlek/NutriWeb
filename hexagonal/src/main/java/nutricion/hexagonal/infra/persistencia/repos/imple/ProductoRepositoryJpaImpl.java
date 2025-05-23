package nutricion.hexagonal.infra.persistencia.repos.imple;

import org.springframework.stereotype.Repository;
import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.dominio.interfaces.DeClases.ProductoRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;
import nutricion.hexagonal.infra.persistencia.repos.ProductoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class ProductoRepositoryJpaImpl implements ProductoRepoSalida {
 
    private final ProductoRepository jpa;   
    public ProductoRepositoryJpaImpl(ProductoRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Producto> findByNombre(String nombre) {
        return jpa.findByNombreProducto(nombre).map(this::toDomain);
    }

    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = toEntity(producto);
        ProductoEntity saved = jpa.save(entity);
        return toDomain(saved);
    }
    
    @Override
    public List<Producto> findByNombreContainingIgnoreCase(String nombre) {
        List<ProductoEntity> entidades = jpa.findByNombreProductoContainingIgnoreCase(nombre);
        //map() convierte de ProductoEntity a modelo dominio Producto.
        return entidades.stream()
                .map(entity -> new Producto(entity.getId(), entity.getNombreProducto()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> findById(Integer id) {
            return jpa.findById(id).map(this::toDomain);
    }

    // Entity -> Domain
    public Producto toDomain(ProductoEntity entity) {
        Producto p = new Producto();
        p.setId(entity.getId());
        p.setNombre(entity.getNombreProducto());
        return p;
    }

    // Domain -> Entity
    public ProductoEntity toEntity(Producto producto) {
        ProductoEntity e = new ProductoEntity();
        e.setId(producto.getId());
        e.setNombreProducto(producto.getNombre());
        return e;
    }


}
