package nutricion.hexagonal.infra.adaptadores.entrada;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nutricion.hexagonal.dominio.clases.RecomendacionNutricional;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.RecomendacionNutricionalDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.UsuarioDTO;
import nutricion.hexagonal.infra.aplicacion.RecomendacionNutriService;
import nutricion.hexagonal.infra.aplicacion.UsuarioService;

//filtro pone usuario en contexto de spring =securityContextHolderr . asi que controller puede accedesa authetication auth con auth.getName()
@RestController
@RequestMapping("/api/usuario")
public class UsuarioPerfilController {

    private final UsuarioService usuarioService;
    private final RecomendacionNutriService recomendacionNutricionalService;

    public UsuarioPerfilController(UsuarioService usuarioService,
            RecomendacionNutriService recomendacionNutricionalService) {
        this.usuarioService = usuarioService;
        this.recomendacionNutricionalService = recomendacionNutricionalService;
    }

    // filtro agrega auth al contexto, lo coge de ahi
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioDTO> perfil(Authentication auth) { // obj sring sec que contiene usuario autenticado,
                                                                    // instancia para spring context, token validado.
        String email = auth.getName(); // email <-- El filtro JWT ya puso el usuario aquí (auth) //email del usuario
                                       // autenticado //auth getname. En TokenProvider. get authenticacion(token)=>
                                       // UsernamePasswordAuthenticationToken con el email del usuario como principal.
        // Obtener el usuario desde el servicio
        Usuario usuario = usuarioService.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email));
        // <-- desde el dominio //busca usuario
        return ResponseEntity.ok(new UsuarioDTO(usuario)); // <-- DTO que el frontend espera //devuelve datos
    }

    // Endpoint para editar el perfil del usuario
    @PutMapping("/perfil")
    public ResponseEntity<UsuarioDTO> editarPerfil(@RequestBody UsuarioDTO usuarioDTO, Authentication auth) {
        String email = auth.getName(); // Obtiene el email del usuario autenticado
        // Obtener el usuario desde el servicio
        Usuario usuario = usuarioService.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + email));

        // Actualiza los datos del usuario con la información proveniente del cuerpo de
        // la solicitud
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
        usuario.setContraseña(usuario.getContraseña());
        usuario.setEdad(usuarioDTO.getEdad());
        usuario.setPeso((float) usuarioDTO.getPeso());
        usuario.setAltura((float) usuarioDTO.getAltura());
        usuario.setNivelActividad(usuarioDTO.getNivelActividad());
        usuario.setMetaSalud(usuarioDTO.getMetaSalud());
        usuario.setTipoDieta(usuarioDTO.getTipoDieta());
        // Guardamos los cambios en la base de datos
        Usuario usuarioActualizado = usuarioService.actualizar(usuario);

        // Recalcular recomendación con los nuevos datos
        recomendacionNutricionalService.calcular(usuarioActualizado);

        // Devuelve el perfil actualizado
        return ResponseEntity.ok(new UsuarioDTO(usuarioActualizado));
    }

    @GetMapping("/recomendacion-nutricional")
    public ResponseEntity<RecomendacionNutricionalDTO> obtenerRecomendacion(Authentication auth) {
        String email = auth.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Asumimos que solo hay una recomendación activa por usuario
        RecomendacionNutricional rec = recomendacionNutricionalService.obtenerPorUsuario(usuario.getId());

        return ResponseEntity.ok(new RecomendacionNutricionalDTO(rec));
    }

}
