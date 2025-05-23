package nutricion.hexagonal.infra.persistencia.repos.imple;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.dominio.clases.Actividad;
import nutricion.hexagonal.dominio.interfaces.DeClases.ActividadRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.ActividadEntity;
import nutricion.hexagonal.infra.persistencia.repos.ActividadRepository;

@Repository
public class ActividadRepoImple implements ActividadRepoSalida {

 private final ActividadRepository actividadJpaRepository;

    public ActividadRepoImple(ActividadRepository actividadJpaRepository) {
        this.actividadJpaRepository = actividadJpaRepository;
    }

    @Override
    public Optional<Actividad> findByNivelActividad(String nivelActividad) {
        return actividadJpaRepository.findByNivelActividad(nivelActividad)
                .map(this::toDomain); // Convierte de Entity a modelo de dominio
    }

    private Actividad toDomain(ActividadEntity entity) {
        Actividad actividad = new Actividad();
        actividad.setId(entity.getId());
        actividad.setNivelActividad(entity.getNivelActividad());
        actividad.setFactorActividad(entity.getFactorActividad());
        return actividad;
    }
}
