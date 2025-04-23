package nutricion.hexagonal.infra.seguridad.adaptadores.entrada;

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
