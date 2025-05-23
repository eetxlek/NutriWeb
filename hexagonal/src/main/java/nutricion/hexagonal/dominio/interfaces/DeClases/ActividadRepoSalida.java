package nutricion.hexagonal.dominio.interfaces.DeClases;

import java.util.Optional;
import nutricion.hexagonal.dominio.clases.Actividad;

public interface ActividadRepoSalida {
Optional<Actividad> findByNivelActividad(String nivelActividad);
}
