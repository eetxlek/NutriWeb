package nutricion.hexagonal.infra.persistencia.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.infra.persistencia.entidades.ConsumoEntity;


@Repository
public interface ConsumoRepository extends JpaRepository<ConsumoEntity, Integer> {
   
}

