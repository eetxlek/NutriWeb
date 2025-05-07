package nutricion.hexagonal.infra.adaptadores.entrada.dto;

public class JwtResponseDTO {
    // modelo de respuesta de la auth
    private String token;

    private String email;

    public JwtResponseDTO(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
