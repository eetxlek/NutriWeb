package nutricion.hexagonal.infra.aplicacion;

import java.util.Optional;

import org.springframework.stereotype.Service;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.UsuarioRepoSalida;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.RegistroRequestDTO;
import nutricion.hexagonal.infra.adaptadores.salida.encrypter.BcryptPasswordEncrypter;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;
import nutricion.hexagonal.infra.persistencia.repos.UsuarioRepoImple;
import nutricion.hexagonal.infra.persistencia.repos.UsuarioRepository;

//Aplica reglas de negocio, en dominio. No tiene @service, se inyecta de config o con adaptador.
//registra usuario, verifica credenciales y busca user por email

//OPERACIONES MAS GENERALES DE USUARIOREPO, buscar registrar verificar   //ENCARGA DE ENCRIPTAR Y VERIFICAR EN LOGIN Y REGISTRO // FOCO SEGURIDAD CREDENCIALES  //nada de token

//Implementa un puerto de entrada. Orquesta el registro de usuarios, validación de existencia . uSA PUERTOS DE SALIDA: usuarioRepo (guardar buscar), encrypt(encriptar)
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final BcryptPasswordEncrypter passwordEncrypter;

    public UsuarioService(UsuarioRepository usuarioRepo, BcryptPasswordEncrypter encrypter) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncrypter = encrypter;
    }

    public void registrar(RegistroRequestDTO dto) {
        if (usuarioRepo.findByCorreoElectronico(dto.getCorreoElectronico()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }
        String encriptado = passwordEncrypter.encode(dto.getPassword());
        Usuario user = fromDto(dto, encriptado);
        UsuarioEntity entidad = toEntity(user);
        // se encriptará aqui
        usuarioRepo.save(entidad);
    }

    public Usuario actualizar(Usuario dto) {
        Optional<UsuarioEntity> usuarioExistente = usuarioRepo.findByCorreoElectronico(dto.getCorreoElectronico());
        if (!usuarioExistente.isPresent()) {
            throw new RuntimeException("El usuario no existe");
        }
        UsuarioEntity entidad = toEntity(dto);
        usuarioRepo.save(entidad);
        return dto;

    }

    public boolean verificarLogin(String email, String rawPassword) {
        return usuarioRepo.findByCorreoElectronico(email)
                .map(user -> passwordEncrypter.matches(rawPassword, user.getContraseña()))
                .orElse(false);
    }

    // Método adaptado para buscar usuario por email
    public boolean existeEmail(String email) {
        return usuarioRepo.existsByCorreoElectronico(email);
    }

    public Usuario buscarPorEmail(String email) {
        // Buscar el usuario por correo electrónico
        UsuarioEntity usuarioEntity = usuarioRepo.findByCorreoElectronico(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
        return fromEntity(usuarioEntity);

    }

    public Usuario fromEntity(UsuarioEntity entity) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(entity.getIdUsuario());
        usuario.setNombre(entity.getNombre());
        usuario.setCorreoElectronico(entity.getCorreoElectronico());
        usuario.setEdad(entity.getEdad());
        usuario.setPeso(entity.getPeso());
        usuario.setAltura(entity.getAltura());
        usuario.setNivelActividad(entity.getNivelActividad());
        usuario.setMetaSalud(entity.getMetaSalud());
        usuario.setTipoDieta(entity.getTipoDieta());
        usuario.setTipoUsuario(entity.getTipoUsuario());
        return usuario;
    }

    public Usuario fromDto(RegistroRequestDTO dto, String encriptada) {

        return new Usuario(
                0,
                dto.getNombre(),
                dto.getCorreoElectronico(),
                encriptada,
                dto.getEdad(),
                (float) dto.getPeso(),
                (float) dto.getAltura(),
                dto.getNivelActividad(),
                dto.getMetaSalud(),
                dto.getTipoDieta(),
                dto.getTipoUsuario());
    }

    UsuarioEntity toEntity(Usuario usuario) { // se espera que ya venga encriptada
        // encripta al crear UsuarioEntity

        return new UsuarioEntity(
                usuario.getIdUsuario(),
                usuario.getNombre(), // Suponiendo que la clase Usuario tiene el campo nombre
                usuario.getCorreoElectronico(),
                usuario.getContraseña(), // Aquí ya viene la contraseña encriptada por el servicio // separa
                                         // persistencia de logica cript
                usuario.getEdad(), // Suponiendo que la clase Usuario tiene estos campos
                usuario.getPeso(),
                usuario.getAltura(),
                usuario.getNivelActividad(),
                usuario.getMetaSalud(),
                usuario.getTipoDieta(),
                usuario.getTipoUsuario());
    }
}
