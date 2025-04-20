package nutricion.hexagonal.infra.persistencia;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;


// infraestructura/persistencia/UsuarioEntity.java
//TABLA DONDE ALMACENA
@Entity
@Table(name = "Usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo_electronico", unique = true)
    private String correoElectronico;

    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "edad")
    private int edad;

    @Column(name = "peso")
    private float peso;

    @Column(name = "altura")
    private float altura;

  
    @Column(name = "nivel_actividad")
    private String nivelActividad;

    @Column(name = "meta_salud")
    private String metaSalud;


    @Column(name = "tipo_dieta")
    private String tipoDieta;

   
    @Column(name = "tipo_usuario")
    private String tipoUsuario;


    public UsuarioEntity() {
    }

    public UsuarioEntity(int idUsuario, String correoElectronico, String contraseña, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioEntity(int idUsuario, String nombre, String correoElectronico, String contraseña, int edad, float peso,
            float altura, String nivelActividad, String metaSalud, String tipoDieta, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.nivelActividad = nivelActividad;
        this.metaSalud = metaSalud;
        this.tipoDieta = tipoDieta;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
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





}


