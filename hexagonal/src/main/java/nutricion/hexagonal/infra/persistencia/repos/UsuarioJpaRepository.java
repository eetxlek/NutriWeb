package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;


//consulta por email con Data JPA
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByCorreoElectronico(String correoElectronico);
}


