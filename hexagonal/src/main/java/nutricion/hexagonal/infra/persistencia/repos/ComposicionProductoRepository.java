package nutricion.hexagonal.infra.persistencia.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;

@Repository
public interface ComposicionProductoRepository extends JpaRepository<ComposicionEntity, Integer> {}

