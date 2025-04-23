package nutricion.hexagonal.infra.seguridad.adaptadores.entrada.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class LoginRequestDTO {
    //clase para recibir los datos del login

    // @NotBlank
    // @Size(min = 3, max = 40)
    // private String name;

    @NotBlank
    @Size(max = 40, min = 0)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    // public LoginRequestDTO(String name, String email, String password) {
    //     this.name = name;
    //     this.email = email;
    //     this.password = password;
    // }
    

    public LoginRequestDTO(@NotBlank @Size(max = 40, min = 0) @Email String email,
            @NotBlank @Size(min = 6, max = 20) String password) {
        this.email = email;
        this.password = password;
    }


    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}