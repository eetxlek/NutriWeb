package nutricion.hexagonal.dominio.interfaces.DeClases;

import java.util.Optional;
import nutricion.hexagonal.dominio.clases.Composicion;

public interface ComposicionRepoSalida {
    Optional<Composicion> obtenerPorIdProducto(Integer id_Producto);
    boolean existsByProducto_Id(Integer id_Producto);
    void guardar(Composicion composicion);
}

