package nutricion.hexagonal.dominio.logica;

import org.springframework.stereotype.Service;

import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.PasswordEncrypter;
import nutricion.hexagonal.dominio.interfaces.UsuarioRepo;
import nutricion.hexagonal.infra.seguridad.token.TokenService;

//verifica credencial- > delega generacion de token a TokenService
//servicio de autenticacion que orquesta proceso de log in. no se encarga del toekn en si y la seguridad.
//servicio que ve si user existe y la contraseña es válida, valida que es quien dice ser y ello da paso a generar un token.
//es cparte de la capa de logica de negocio // depende de interfaz, es buena practica para dar flexibilidad a las pruebas de implementaciones diferentes.
@Service
public class AuthService {
    private final TokenService tokenService; // <- Depende de la interfaz
    private final UsuarioRepo usuarioRepository;
    private final PasswordEncrypter passwordEncrypter;

    public AuthService(TokenService tokenService, UsuarioRepo usuarioRepository, PasswordEncrypter passwordEncrypter) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncrypter = passwordEncrypter;
    }

    public String login(String email, String password) {   //LO MAS IMPORTANTE DE ESTA CLASE. AQUI REPO SE USA PARA AUTENTICAR  // FOCO EN EMISION DE TOKEN
        Usuario usuario = usuarioRepository.buscarPorEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncrypter.matches(password, usuario.getContraseña())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return tokenService.generarToken(String.valueOf(usuario.getCorreoElectronico()));
    }

}
