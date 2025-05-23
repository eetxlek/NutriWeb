package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByCorreoElectronico(String correoElectronico);
    boolean existsByCorreoElectronico(String correoElectronico);
    
    @EntityGraph(attributePaths = "consumos")  //al buscar usuario tambien carga sus consumos asociados. Evita LazyInitializationException
    Optional<UsuarioEntity> findWithConsumosByCorreoElectronico(String email);

    @EntityGraph(attributePaths = "despensa")
    Optional<UsuarioEntity> findWithDespensaByCorreoElectronico(String email);

}


