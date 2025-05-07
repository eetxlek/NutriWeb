package nutricion.hexagonal.infra.aplicacion;

import org.springframework.stereotype.Service;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.UsuarioRepoSalida;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.RegistroRequestDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.UsuarioMapper;
import nutricion.hexagonal.infra.adaptadores.salida.encrypter.BcryptPasswordEncrypter;

//Aplica reglas de negocio, en dominio. No tiene @service, se inyecta de config o con adaptador.
//registra usuario, verifica credenciales y busca user por email

//OPERACIONES MAS GENERALES DE USUARIOREPO, buscar registrar verificar   //ENCARGA DE ENCRIPTAR Y VERIFICAR EN LOGIN Y REGISTRO // FOCO SEGURIDAD CREDENCIALES  //nada de token

//Implementa un puerto de entrada. Orquesta el registro de usuarios, validación de existencia . uSA PUERTOS DE SALIDA: usuarioRepo (guardar buscar), encrypt(encriptar)
@Service
public class UsuarioService {

    private final UsuarioRepoSalida usuarioRepo;
    private final UsuarioMapper usuarioMapper;
    private final BcryptPasswordEncrypter passwordEncrypter;

    public UsuarioService(UsuarioRepoSalida usuarioRepo, BcryptPasswordEncrypter encrypter,
            UsuarioMapper usuarioMapper) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncrypter = encrypter;
        this.usuarioMapper = usuarioMapper;
        ;
    }

    public void registrar(RegistroRequestDTO dto) {
        if (usuarioRepo.buscarPorEmail(dto.getCorreoElectronico()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        String encriptado = passwordEncrypter.encode(dto.getPassword());
        Usuario user = usuarioMapper.fromDto(dto, encriptado);
        // se encriptará aqui
        usuarioRepo.guardar(user);
    }

    public boolean verificarLogin(String email, String rawPassword) {
        return usuarioRepo.buscarPorEmail(email)
                .map(user -> passwordEncrypter.matches(rawPassword, user.getContraseña()))
                .orElse(false);
    }

    // Método adaptado para buscar usuario por email
    public boolean existeEmail(String email) {
        return usuarioRepo.existeEmail(email);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepo.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

}
