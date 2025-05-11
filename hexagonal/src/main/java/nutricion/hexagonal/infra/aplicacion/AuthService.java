package nutricion.hexagonal.infra.aplicacion;

import org.springframework.stereotype.Service;

import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.PasswordEncrypter;
import nutricion.hexagonal.dominio.interfaces.DeClases.UsuarioRepoSalida;
import nutricion.hexagonal.infra.adaptadores.salida.security.TokenService;

//verifica credencial- > delega generacion de token a TokenService
//servicio de autenticacion que orquesta proceso de log in. no se encarga del toekn en si y la seguridad.
//servicio que ve si user existe y la contraseña es válida, valida que es quien dice ser y ello da paso a generar un token.
//es cparte de la capa de logica de negocio // depende de interfaz, es buena practica para dar flexibilidad a las pruebas de implementaciones diferentes.

//mplementa un puerto de ENTRADA . Orquesta la lógica de login o validación de tokens. Usa puertos de salida: usuariorepo, passencryp, tokenservice
@Service
public class AuthService {
    private final TokenService tokenService; // <- Depende de la interfaz
    private final UsuarioRepoSalida usuarioRepository;
    private final PasswordEncrypter passwordEncrypter;

    public AuthService(TokenService tokenService, UsuarioRepoSalida usuarioRepository, PasswordEncrypter passwordEncrypter) {
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
