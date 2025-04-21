package nutricion.hexagonal.infra.seguridad;

import org.springframework.stereotype.Component;

import nutricion.hexagonal.dominio.logica.TokenService;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Component
public class JwtTokenProvider {
 //clase que implementa la logica e interaccion con token JWT, totalmente infra.
    private final TokenService tokenService; // Clase que ya hiciste, con validateToken(), etc.

    public JwtTokenProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    public String generarToken(String userId) {
        return tokenService.generarToken(userId); // Este método debe existir en TokenService
    }

    public boolean validateToken(String token) {
        return tokenService.validarToken(token);
    }

    public Authentication getAuthentication(String token) {
        String userId = tokenService.extraerUserId(token);
        // Aquí podrías obtener más datos del usuario (roles, etc.)
        return new UsernamePasswordAuthenticationToken(userId, null, List.of());
    }
}

