package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;


//consulta por email con Data JPA
//define operaciones de la base de datos
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByCorreoElectronico(String correoElectronico);
    boolean existsByCorreoElectronico(String correoElectronico);

}


