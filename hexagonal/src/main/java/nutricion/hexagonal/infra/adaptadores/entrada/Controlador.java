package nutricion.hexagonal.infra.adaptadores.entrada;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//distinto al otro controller que devueve json, este devuelve vistas thymeleaf, (o jps, oosea respuestas html para navegador
//no devuelve datos sino vistas.
@Controller
public class Controlador {


    @GetMapping("/")
    public String mostrarInicio() {
        return "landing";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro"; // Thymeleaf buscará registro.html en templates
    }

    @GetMapping("/perfil")
    public String mostrarPerfil() {
        return "perfil"; // Thymeleaf buscará registro.html en templates
    }

    @GetMapping("/editar-perfil")
    public String editarPerfil() {
        // if (auth == null || !auth.isAuthenticated()) {
        //     return "redirect:/login";
        // }
        // String email = auth.getName(); // null porque lo pasas en el fetch
        // Usuario usuario = usuarioService.buscarPorEmail(email); // Asegúrate de tener este método
        // model.addAttribute("usuario", usuario);

        return "editar-perfil"; // Thymeleaf buscará registro.html en templates
    }

    // login dbee ser post por seguridad. No cambia nada el objetivo es obtener el
    // token
    // GET login es para mostrar el formulario, la vista // POST registro es para
    // mandar datos al backend, validar
    @GetMapping("/login")
    public String loginPage() {

        return "login"; // carga templates/login.html
    }

    @GetMapping("/productos")
    public String verProductos() {
        return "productos";
    }

    @GetMapping("/productosapi")
    public String verProductosApi() {
        return "productos2";
    }
}
