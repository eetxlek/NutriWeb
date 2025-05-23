package nutricion.hexagonal.infra.adaptadores.salida.security;

import org.springframework.stereotype.Component;

import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.infra.aplicacion.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// Adaptador JWT-spring security CREA Authenticcation con tokenservice.  crea y valida token
// traduce token en objeto authenticacion para que spring security lo reconozca y use.
@Component
public class JwtTokenProvider {
    // clase que implementa la logica e interaccion con token JWT (infra).
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    public JwtTokenProvider(TokenService tokenService, UsuarioService usuarioService) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    public String generarToken(String email) {
        return tokenService.generarToken(email); 
    }

    public boolean validateToken(String token) { // da booleano tras verificar token fon la firma.
        return tokenService.validarToken(token);
    }

    // LO IMPORTANTE
    public Authentication getAuthentication(String token) { // saca email del token y devuelve authentication.

        String email = tokenService.extraerEmail(token);
        // Aquí podrías obtener más datos del usuario (roles, etc. para más adelante)

        Usuario usuario = usuarioService.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
        UserPrincipalAdapter userPrincipal = new UserPrincipalAdapter(usuario);

        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities()); 
    }

}
