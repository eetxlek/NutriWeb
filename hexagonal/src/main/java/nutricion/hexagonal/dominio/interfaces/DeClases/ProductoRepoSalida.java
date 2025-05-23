package nutricion.hexagonal.dominio.interfaces.DeClases;

import java.util.List;
import java.util.Optional;
import nutricion.hexagonal.dominio.clases.Producto;

public interface ProductoRepoSalida {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    Optional<Producto> findByNombre(String nombre);
    Optional<Producto> findById(Integer id);
    Producto save(Producto producto);
    
}
