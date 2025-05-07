
package nutricion.hexagonal.infra.adaptadores.entrada.dto;
import jakarta.validation.constraints.*;

public class RegistroRequestDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 40)
    private String correoElectronico;

    @Min(1)
    @Max(120)
    private int edad;

    @Positive
    private float peso;

    @Positive
    private float altura;

    @NotBlank
    private String nivelActividad;

    @NotBlank
    private String metaSalud;

    @NotBlank
    private String tipoDieta;

    @NotBlank
    private String tipoUsuario;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    
    // Getters y Setters

    public RegistroRequestDTO(@NotBlank String nombre, @NotBlank @Email @Size(max = 40) String correoElectronico,
            @Min(1) @Max(120) int edad, @Positive float peso, @Positive float altura, @NotBlank String nivelActividad,
            @NotBlank String metaSalud, @NotBlank String tipoDieta, @NotBlank String tipoUsuario,
            @NotBlank @Size(min = 6, max = 20) String password) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.nivelActividad = nivelActividad;
        this.metaSalud = metaSalud;
        this.tipoDieta = tipoDieta;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getNivelActividad() {
        return nivelActividad;
    }

    public void setNivelActividad(String nivelActividad) {
        this.nivelActividad = nivelActividad;
    }

    public String getMetaSalud() {
        return metaSalud;
    }

    public void setMetaSalud(String metaSalud) {
        this.metaSalud = metaSalud;
    }

    public String getTipoDieta() {
        return tipoDieta;
    }

    public void setTipoDieta(String tipoDieta) {
        this.tipoDieta = tipoDieta;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public void crearUsuario(RegistroRequestDTO dto) {
    // Usuario usuario = new Usuario();

    // usuario.setNombre(dto.getNombre());
    // usuario.setCorreoElectronico(dto.getCorreoElectronico());
    // usuario.setEdad(dto.getEdad());
    // usuario.setPeso(dto.getPeso());
    // usuario.setAltura(dto.getAltura());
    // usuario.setNivelActividad(dto.getNivelActividad());
    // usuario.setMetaSalud(dto.getMetaSalud());
    // usuario.setTipoDieta(dto.getTipoDieta());
    // usuario.setTipoUsuario(dto.getTipoUsuario());
    // }
}
