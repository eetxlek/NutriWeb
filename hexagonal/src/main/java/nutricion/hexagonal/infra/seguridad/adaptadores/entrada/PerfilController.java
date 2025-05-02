package nutricion.hexagonal.infra.seguridad.adaptadores.entrada;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nutricion.hexagonal.dominio.UsuarioService;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.infra.seguridad.adaptadores.entrada.dto.UsuarioDTO;


//filtro pone usuario en contexto de spring =securityContextHolderr . asi que controller puede accedesa authetication auth con auth.getName()
@RestController
@RequestMapping("/api/usuario")
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    //filtro agrega auth al contexto, lo coge de ahi
     @GetMapping("/perfil")
    public ResponseEntity<UsuarioDTO> perfil(Authentication auth) { //obj sring sec que contiene usuario autenticado, instancia para spring context, token validado.
        String email = auth.getName(); // email <-- El filtro JWT ya puso el usuario aquí (auth) //email del usuario autenticado  //auth getname. En TokenProvider. get authenticacion(token)=> UsernamePasswordAuthenticationToken con el email del usuario como principal.
        Usuario usuario = usuarioService.buscarPorEmail(email); // <-- desde el dominio  //busca usuario
        return ResponseEntity.ok(new UsuarioDTO(usuario)); // <-- DTO que el frontend espera  //devuelve datos
    }
    // @PostMapping("/registro")
    // public ResponseEntity<UsuarioDTO> registro(Authentication auth) { //obj sring sec que contiene usuario autenticado, instancia para spring context, token validado.
    //     String email = auth.getName(); // email <-- El filtro JWT ya puso el usuario aquí (auth) //email del usuario autenticado  //auth getname. En TokenProvider. get authenticacion(token)=> UsernamePasswordAuthenticationToken con el email del usuario como principal.
    //     Usuario usuario = usuarioService.buscarPorEmail(email); // <-- desde el dominio  //busca usuario
    //     usuarioService.registrar()
    //     return ResponseEntity.ok(new UsuarioDTO(usuario)); // <-- DTO que el frontend espera  //devuelve datos
    // }
    
}
