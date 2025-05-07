package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.infra.persistencia.entidades.DespensaEntity;


@Repository
public interface DespensaRepository extends JpaRepository<DespensaEntity, Integer> {
    // Métodos adicionales si es necesario
    Optional<DespensaEntity> findDespensaByUsuarioIdAndProductoId(int idUsuario, Integer idProducto);

    //Optional<ProductoEntity> findByUsuarioIdAndProducto_Id(int idUsuario, Integer idProducto);

}
