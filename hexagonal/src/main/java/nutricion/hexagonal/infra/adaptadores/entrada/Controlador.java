package nutricion.hexagonal.infra.adaptadores.entrada;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//distinto al otro controller que devueve json, este devuelve vistas thymeleaf
@Controller
public class Controlador {

    @GetMapping("/navbar-fragment")
    public String getNavbarFragment(Model model) {
    
        return "nav :: navbar"; // Es el nombre del fragmento definido en el archivo nav.html
    }

    @GetMapping("/")
    public String mostrarInicio() {
        return "landing";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @GetMapping("/perfil")
    public String mostrarPerfil() {
        return "perfil";
    }

    @GetMapping("/editar-perfil")
    public String editarPerfil() {
        return "editar-perfil";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    @GetMapping("/productos")
    public String verProductos() {
        return "productos";
    }

    @GetMapping("/productosapi")
    public String verProductosApi() {
        return "productos2";
    }

    @GetMapping("/despensa")
    public String verDespensa() {
        return "despensa";
    }

    @GetMapping("/consumo")
    public String verConsumo() {
        return "consumo";
    }
}
