package nutricion.hexagonal.infra.persistencia.repos;

// infra/jpa/repository/ProductoRepositoryJpaImpl.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.dominio.interfaces.ProductoRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//implementa las operaciones definidas
@Repository
public class ProductoRepositoryJpaImpl implements ProductoRepoSalida {

    @Autowired
    private ProductoRepoJpa jpa;

    @Override
    public Optional<Producto> findByNombre(String nombre) {
        return jpa.findByNombreProducto(nombre).map(this::toDomain);
    }

    // hace un insert en la base de datos
    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = toEntity(producto);
        ProductoEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    // Mapeo Entity -> Domain
    public Producto toDomain(ProductoEntity entity) {
        Producto p = new Producto();
        p.setId(entity.getId_producto());
        p.setNombre(entity.getNombreProducto());
        p.setDescripcion(entity.getDescripcion());
        return p;
    }

    // Mapeo Domain -> Entity
    public ProductoEntity toEntity(Producto producto) {
        ProductoEntity e = new ProductoEntity();
        e.setId_producto(producto.getId());
        e.setNombreProducto(producto.getNombre());
        e.setDescripcion(producto.getDescripcion());
        return e;
    }

    @Override
    public List<Producto> findByNombreContainingIgnoreCase(String nombre) {
        List<ProductoEntity> entidades = jpa.findByNombreProductoContainingIgnoreCase(nombre);
        //map() convierte de ProductoEntity a modelo dominio Producto.
        return entidades.stream()
                .map(entity -> new Producto(entity.getId_producto(), entity.getNombreProducto(), entity.getDescripcion()))
                .collect(Collectors.toList());
    }

}
