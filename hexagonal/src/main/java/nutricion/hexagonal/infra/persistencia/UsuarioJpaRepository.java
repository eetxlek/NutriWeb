package nutricion.hexagonal.infra.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


//consulta por email con Data JPA
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByEmail(String email);
}


