package nutricion.hexagonal.infra.seguridad.adaptadores.entrada;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

    @GetMapping("/navbar-fragment")
    public String getNavbarFragment(Model model) {
        // El fragmento de navbar se define como "nav :: navbar" en Thymeleaf
        // Esto devolverá solo la parte del nav
        return "nav :: navbar";  // Es el nombre del fragmento definido en el archivo nav.html
    }
}
