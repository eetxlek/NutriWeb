package nutricion.hexagonal.infra.seguridad.adaptadores.entrada.dto;

import nutricion.hexagonal.dominio.clases.Usuario;
public class UsuarioDTO {

    private String nombre;
    private String correoElectronico;
    private int edad;
    private double peso;
    private double altura;
    private String nivelActividad;
    private String metaSalud;
    private String tipoDieta;
    private String tipoUsuario;

    // Constructor que toma un Usuario
    public UsuarioDTO(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.correoElectronico = usuario.getCorreoElectronico();
        this.edad = usuario.getEdad();
        this.peso = usuario.getPeso();
        this.altura = usuario.getAltura();
        this.nivelActividad = usuario.getNivelActividad();
        this.metaSalud = usuario.getMetaSalud();
        this.tipoDieta = usuario.getTipoDieta();
        this.tipoUsuario = usuario.getTipoUsuario();
    }

    // Getters y Setters
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

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
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
}


