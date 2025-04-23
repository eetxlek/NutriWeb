package nutricion.hexagonal.infra.seguridad.token;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


//adaptador spring security CREA Authenticcation con tokenservice. // tokenserrvice crea y valida token
//traduce token en objeto authenticacion para que spring security lo use. // podria haber usado en vez de toenservice el tokenadapter (pero este era menos robusto)
@Component
public class JwtTokenProvider {
 //clase que implementa la logica e interaccion con token JWT, totalmente infra.
    private final TokenService tokenService; // Clase que ya hiciste, con validateToken(), etc.

    public JwtTokenProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    public String generarToken(String userId) {
        return tokenService.generarToken(userId); //  Da id
    }

    public boolean validateToken(String token) {  //da booleano tras verificar token fon la firma.
        return tokenService.validarToken(token);
    }

    public Authentication getAuthentication(String token) {
        String userId = tokenService.extraerUserId(token);
        // Aquí podrías obtener más datos del usuario (roles, etc.)
        return new UsernamePasswordAuthenticationToken(userId, null, List.of());
    }
}

