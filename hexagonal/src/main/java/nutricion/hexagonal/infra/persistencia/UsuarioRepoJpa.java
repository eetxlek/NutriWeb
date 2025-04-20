package nutricion.hexagonal.infra.persistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.UsuarioRepo;

import java.util.Optional;
import java.util.List;

// infraestructura/persistencia/UsuarioRepositoryJpa.java
//adaptador
@Repository
public class UsuarioRepoJpa implements UsuarioRepo {   //convierte UsuarioJPA en Usuario
    
    @Autowired
    private UsuarioJpaRepository jpaRepo;  // Spring Data JPA //la interfaz repo de INFRA la que te permite buscar por mail. 
    //es esto: 
    // public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, String> {
    // Optional<UsuarioEntity> findByEmail(String email);


    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepo.findByCorreoElectronico(email)
            .map(this::toDomain);
    }

    private Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
            entity.getIdUsuario(),
            entity.getCorreoElectronico(),
            entity.getContraseña(),
            entity.getTipoUsuario()) // Conversión a List<String>
        ;
    }
    private UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
            usuario.getIdUsuario(),
            usuario.getCorreoElectronico(),
            usuario.getContraseña(),
            usuario.getTipoUsuario()
        );
    }
    

    @Override
    public void guardar(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);  // convertimos a entidad JPA
        jpaRepo.save(entity);  // lo guardamos con Spring Data
    }
}
