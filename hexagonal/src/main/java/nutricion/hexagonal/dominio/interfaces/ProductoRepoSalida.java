package nutricion.hexagonal.dominio.interfaces;

import java.util.List;
import java.util.Optional;

import nutricion.hexagonal.dominio.clases.Producto;
//es implementado por adaptador
public interface ProductoRepoSalida {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    Optional<Producto> findByNombre(String nombre);
    Producto save(Producto producto);
    
}
