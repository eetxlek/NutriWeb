package nutricion.hexagonal.dominio.interfaces;

// Dominio: interfaz (puerto de salida)
public interface Token {
    String generarToken(String email);  // ¿Qué necesitas?
    boolean validarToken(String token);  // ¿Qué necesitas?
    String extraerEmail(String token);   // ¿Qué necesitas?
}

