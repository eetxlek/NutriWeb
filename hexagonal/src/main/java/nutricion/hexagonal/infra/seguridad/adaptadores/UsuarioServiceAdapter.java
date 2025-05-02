package nutricion.hexagonal.infra.seguridad.adaptadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nutricion.hexagonal.dominio.UsuarioService;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.UsuarioRepo;
import nutricion.hexagonal.infra.seguridad.adaptadores.salida.BcryptPasswordEncrypter;

@Service
public class UsuarioServiceAdapter {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioServiceAdapter(UsuarioService usuarioService) {
        this.usuarioService = usuarioService; // Inyección correct
    }

    // Métodos para delegar las acciones del dominio
    public void registrar(Usuario usuario) {
        usuarioService.registrar(usuario);
    }

    public boolean verificarLogin(String email, String password) {
        return usuarioService.verificarLogin(email, password);
    }
}
