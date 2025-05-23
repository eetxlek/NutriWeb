package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nutricion.hexagonal.infra.persistencia.entidades.ActividadEntity;

@Repository
public interface ActividadRepository extends JpaRepository<ActividadEntity, Integer> {
    Optional<ActividadEntity> findByNivelActividad(String nivelActividad);

}
