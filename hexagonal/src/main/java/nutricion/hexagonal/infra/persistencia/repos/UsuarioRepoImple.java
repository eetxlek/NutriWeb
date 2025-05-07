package nutricion.hexagonal.infra.persistencia.repos;


import org.springframework.stereotype.Repository;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.UsuarioRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;
import java.util.Optional;

// infraestructura/persistencia/UsuarioRepositoryJpa.java
//adaptador de SALIDA, conecta con dominio con jpa
//@repo bien se detecta e inyecta en UsuarioService
@Repository
public class UsuarioRepoImple implements UsuarioRepoSalida {   //convierte UsuarioJPA en Usuario y guarda con Spring data (persistencia)
    //implementa repo de dominio
    
    private final UsuarioRepository jpaRepo;  // Spring Data JPA //la interfaz repo de INFRA la que te permite buscar por mail. 

    public UsuarioRepoImple(UsuarioRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }
    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepo.findByCorreoElectronico(email)
            .map(this::toDomain);
    }
    //convierte userEntity infra a usuario dominio, perfecto
    private Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
            entity.getIdUsuario(),
            entity.getNombre(),  // Suponiendo que la clase Usuario tiene el campo nombre
            entity.getCorreoElectronico(),
            entity.getContraseña(),
            entity.getEdad(),  // Suponiendo que la clase Usuario tiene estos campos
            entity.getPeso(),
            entity.getAltura(),
            entity.getNivelActividad(),
            entity.getMetaSalud(),
            entity.getTipoDieta(),
            entity.getTipoUsuario()) // Conversión a List<String>
        ;
    }
    private UsuarioEntity toEntity(Usuario usuario) {  // se espera que ya venga encriptada
        //encripta al crear UsuarioEntity
      
        return new UsuarioEntity(
            usuario.getIdUsuario(),
            usuario.getNombre(),  // Suponiendo que la clase Usuario tiene el campo nombre
            usuario.getCorreoElectronico(),
            usuario.getContraseña(), // Aquí ya viene la contraseña encriptada por el servicio // separa persistencia de logica cript
            usuario.getEdad(),  // Suponiendo que la clase Usuario tiene estos campos
            usuario.getPeso(),
            usuario.getAltura(),
            usuario.getNivelActividad(),
            usuario.getMetaSalud(),
            usuario.getTipoDieta(),
            usuario.getTipoUsuario()
        );
    }
    
    //HYA QUE ENCRIPTAR LA CONTRASEÑA
    @Override
    public void guardar(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);  // convertimos dominio MODELO a ENTIDAD JPA
        jpaRepo.save(entity);  // lo GUARDA con Spring Data (persistencia)
    }
    @Override
    public boolean existeEmail(String email) {
        return jpaRepo.existsByCorreoElectronico(email);
    }
}
