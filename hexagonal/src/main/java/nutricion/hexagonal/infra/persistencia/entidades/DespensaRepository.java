package nutricion.hexagonal.infra.persistencia.entidades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespensaRepository extends JpaRepository<DespensaEntity, Integer> {}
