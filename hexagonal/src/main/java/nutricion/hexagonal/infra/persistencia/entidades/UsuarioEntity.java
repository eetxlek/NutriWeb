package nutricion.hexagonal.infra.persistencia.entidades;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo_electronico", unique = true)
    private String correoElectronico;

    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "edad")
    private int edad;

    @Column(name = "sexo")
    private String sexo;

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

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<ConsumoEntity> consumos;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<DespensaEntity> despensa;

    public UsuarioEntity() {
    }

    public UsuarioEntity(int id, String nombre, String correoElectronico, String contraseña, int edad, String sexo, float peso,
            float altura, String nivelActividad, String metaSalud, String tipoDieta, String tipoUsuario,
            Set<ConsumoEntity> consumos, Set<DespensaEntity> despensa) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.edad = edad;
        this.sexo=sexo;
        this.peso = peso;
        this.altura = altura;
        this.nivelActividad = nivelActividad;
        this.metaSalud = metaSalud;
        this.tipoDieta = tipoDieta;
        this.tipoUsuario = tipoUsuario;
        this.consumos = consumos;
        this.despensa = despensa;
    }

    public UsuarioEntity(int idUsuario, String correoElectronico, String contraseña, String tipoUsuario) {
        this.id = idUsuario;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioEntity(int idUsuario, String nombre, String correoElectronico, String contraseña, int edad,
            String sexo,float peso,
            float altura, String nivelActividad, String metaSalud, String tipoDieta, String tipoUsuario) {
        this.id = idUsuario;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.edad = edad;
          this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.nivelActividad = nivelActividad;
        this.metaSalud = metaSalud;
        this.tipoDieta = tipoDieta;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public Set<ConsumoEntity> getConsumos() {
        return consumos;
    }

    public void setConsumos(Set<ConsumoEntity> consumos) {
        this.consumos = consumos;
    }

    public Set<DespensaEntity> getDespensa() {
        return despensa;
    }

    public void setDespensa(Set<DespensaEntity> despensa) {
        this.despensa = despensa;
    }

  

}
