package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.infra.persistencia.entidades.RecomendacionNutricionalEntity;

@Repository
public interface RecomendNutriRepository extends JpaRepository<RecomendacionNutricionalEntity, Integer> {
   Optional<RecomendacionNutricionalEntity> findByUsuario_Id(int idUsuario);

}
