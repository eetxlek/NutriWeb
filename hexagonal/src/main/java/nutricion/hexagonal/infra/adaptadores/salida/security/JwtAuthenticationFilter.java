package nutricion.hexagonal.infra.adaptadores.salida.security;

import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { // ANTES DE LLEGAR A CONTROLLADORES de backend

    private final JwtTokenProvider tokenProvider; // Clase que manejar JWT

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // System.out.println("JwtAuthenticationFilter activo");
        // // Mostrar información adicional de la solicitud (request)
        // System.out.println("Método de solicitud: " + request.getMethod()); // Ver el método (GET, POST, etc.)
        // System.out.println("URL de la solicitud: " + request.getRequestURL()); // Ver la URL completa de la solicitud
        // System.out.println("Headers de la solicitud: ");
        // // Ver qué hay en el Authorization header
        // System.out.println("Authorization header recibido: " + request.getHeader("Authorization"));
        // // 1. Obtener el token JWT de las cabeceras de la solicitud.
        String token = obtenerTokenDeRequest(request);

        // System.out.println("Authorization header recibido: " + request.getHeader("Authorization"));
        // System.out.println("Token extraído: " + token);
        // 2. Si el token es válido, obtener la autenticación y establecerla en el contexto PROVIDER valida
        if (token != null && tokenProvider.validateToken(token)) {
          
            Authentication auth = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        // 3. Continuar con el filtro con usuario autenticado en el sprin security context
        chain.doFilter(request, response);
    }

    // antes de que spring llame al filtro, llama aqui y si da true, filtro JWT no se ejecuta
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
  
        return  path.equals("/api/auth/login") || path.equals("/productosapi") || path.equals("/despensa") || path.equals("/consumo") || path.equals("/favicon.ico") || path.equals("/navbar-fragment")
        ||  path.equals("/") || path.equals("/auth.js") || path.equals("/login")|| path.equals("/api/auth/registro")
                || path.startsWith("/api/auth") || path.startsWith("/productos") || path.startsWith("/api/producto");
    }

    private String obtenerTokenDeRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization"); // Obtiene la cabecera
        // System.out.println("FILTRO: obtener bearertoken: de header" + bearerToken);
        // System.out.println("FILTRO: si no es null validará el provides y lo mete en CONTEXT");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Extrae el token
        }
        return null;
    }

}