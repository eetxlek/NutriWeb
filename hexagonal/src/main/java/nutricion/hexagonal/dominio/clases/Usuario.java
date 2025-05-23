package nutricion.hexagonal.dominio.clases;




//SIMPLE MODELO 
public class Usuario {
    private int id; // Correspondiente a `id_usuario`
    private String nombre; // Correspondiente a `nombre`
    private String correoElectronico; // Correspondiente a `correo_electronico`
    private String contraseña; // Correspondiente a `contraseña` (guardada encriptada)
    private int edad; // Correspondiente a `edad`
    private String sexo;
    private float peso; // Correspondiente a `peso`
    private float altura; // Correspondiente a `altura`
    private String nivelActividad; // Correspondiente a `nivel_actividad` (ENUM)
    private String metaSalud; // Correspondiente a `meta_salud`
    private String tipoDieta; // Correspondiente a `tipo_dieta` (ENUM)
    private String tipoUsuario; // Correspondiente a `tipo_usuario` (ENUM)


    public Usuario() {
    }
    public Usuario(int idUsuario, String nombre, String correoElectronico, String contraseña, int edad, String sexo, float peso,
            float altura, String nivelActividad, String metaSalud, String tipoDieta, String tipoUsuario) {
        this.id = idUsuario;
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
    }
    public Usuario(int idUsuario, String correoElectronico, String contraseña, String tipoUsuario) {
        this.id = idUsuario;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
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