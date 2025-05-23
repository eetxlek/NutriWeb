package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;

@Repository
public interface ComposicionProductoRepository extends JpaRepository<ComposicionEntity, Integer> {
    Optional<ComposicionEntity> findByProducto_Id(Integer id); 
    boolean existsByProducto_Id(Integer idProducto);

}

