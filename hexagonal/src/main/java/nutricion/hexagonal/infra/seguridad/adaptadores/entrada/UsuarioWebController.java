package nutricion.hexagonal.infra.seguridad.adaptadores.entrada;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import nutricion.hexagonal.dominio.UsuarioService;
import nutricion.hexagonal.dominio.clases.Usuario;

// web/controller/UsuarioWebController.java
//recibe GET y POST puede usar DTOs /// llama a servicios de dominio como UsuarioService
// @Controller
// public class UsuarioWebController {

//     private final UsuarioService usuarioService;

//     public UsuarioWebController(UsuarioService usuarioService) { //si recibe RegistroDTO, convierte a usuario, llama a service.registrar(usuario). Servicio encripta.
//         this.usuarioService = usuarioService;
//     }

//        @GetMapping("/registro")
//     public String mostrarFormularioRegistro(Model model) {
//         model.addAttribute("usuarioRegistro", new UsuarioRegistroDto());
//         return "registro"; // Thymeleaf: registro.html
//     }

//     @PostMapping("/registro")
//     public String registrar(@ModelAttribute("usuarioRegistro") UsuarioRegistroDto dto, Model model) {
//         // Validación simple
//         if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
//             model.addAttribute("error", "Las contraseñas no coinciden");
//             return "registro";
//         }

//         // Convertir DTO a dominio
//         Usuario nuevoUsuario = new Usuario(
//             null, // o UUID.randomUUID() si se maneja en app
//             dto.getCorreoElectronico(),
//             dto.getPassword(), // idealmente encriptada en el servicio
//             List.of("USUARIO") // tipoUsuario por defecto
//         );

//         try {
//             usuarioService.registrar(nuevoUsuario);
//             return "redirect:/login";
//         } catch (Exception e) {
//             model.addAttribute("error", e.getMessage());
//             return "registro";
//         }
//     }
// }

