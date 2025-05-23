package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.infra.persistencia.entidades.DespensaEntity;

@Repository
public interface DespensaRepository extends JpaRepository<DespensaEntity, Integer> {

    DespensaEntity findByUsuario_CorreoElectronicoAndProducto_Id(String correoElectronico, Integer idProducto);
    boolean existsByUsuarioCorreoElectronicoAndProductoId(String email, Integer id);
    Optional<DespensaEntity> findById(Integer id);
    Set<DespensaEntity> findByUsuarioCorreoElectronico(String correoElectronico);
}
