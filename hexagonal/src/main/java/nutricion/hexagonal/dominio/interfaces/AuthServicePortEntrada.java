package nutricion.hexagonal.dominio.interfaces;


//Es un puerto de ENTRADA: define un caso de uso (ej. login, validar token).
//Lo implementa un servicio de aplicación (AuthService) que usa componentes del dominio y adaptadores externos.

// Caso de uso (puerto de entrada). // Implementación del caso de uso @Service public class AuthService 

//auth representa el qué quiere hacer y el usuariorepo representa cómo (con dependencia externa)
public interface AuthServicePortEntrada {
    String authenticate(String email, String password);
    boolean validateToken(String token);
    String extractUserId(String token);
}

