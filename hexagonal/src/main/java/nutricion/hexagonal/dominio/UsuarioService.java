package nutricion.hexagonal.dominio;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.PasswordEncrypter;
import nutricion.hexagonal.dominio.interfaces.UsuarioRepo;
import nutricion.hexagonal.infra.seguridad.adaptadores.salida.BcryptPasswordEncrypter;

//Aplica reglas de negocio, en dominio. No tiene @service, se inyecta de config o con adaptador.
//registra usuario, verifica credenciales y busca user por email

//OPERACIONES MAS GENERALES DE USUARIOREPO, buscar registrar verificar   //ENCARGA DE ENCRIPTAR Y VERIFICAR EN LOGIN Y REGISTRO // FOCO SEGURIDAD CREDENCIALES  //nada de token
@Service
public class UsuarioService {

    private final UsuarioRepo usuarioRepo;
    private final BcryptPasswordEncrypter passwordEncrypter;

    public UsuarioService(UsuarioRepo usuarioRepo, BcryptPasswordEncrypter encrypter) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncrypter = encrypter;
    }

    public void registrar(Usuario usuario) {
        if (usuarioRepo.buscarPorEmail(usuario.getCorreoElectronico()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }
    
        // ✅ Encriptar la contraseña ANTES de guardar
        String hashedPassword = passwordEncrypter.encode(usuario.getContraseña());
        usuario.setContraseña(hashedPassword);
    
        usuarioRepo.guardar(usuario);
    }
    

    public boolean verificarLogin(String email, String rawPassword) {
        return usuarioRepo.buscarPorEmail(email)
                .map(user -> passwordEncrypter.matches(rawPassword, user.getContraseña()))
                .orElse(false);
    }
    // Método adaptado para buscar usuario por email
    public Usuario buscarPorEmail(String email) {
        return usuarioRepo.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
}
