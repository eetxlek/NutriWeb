package nutricion.hexagonal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping("/")
    public String mostrarInicio() {
        return "landing";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        //si ya esta autenticado a perfil
        // if (getAuthenticatedUser() != null) {
        //     return "perfil";
        // }
        //para ir al formulario
        return "login"; // carga templates/login.html
    }
}
