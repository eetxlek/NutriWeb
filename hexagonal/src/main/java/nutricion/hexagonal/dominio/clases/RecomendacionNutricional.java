package nutricion.hexagonal.dominio.clases;



public class RecomendacionNutricional {
 
    private Integer id;
    private Usuario usuario;   
    private Actividad actividad;
    private float caloriasRecomendadas;
    private float porcentajeProteinas;
    private float porcentajeGrasas;
    private float porcentajeCarbohidratos;

    
    public RecomendacionNutricional() {
    }

    public RecomendacionNutricional(Integer id, Usuario usuario, Actividad actividad, float caloriasRecomendadas,
            float porcentajeProteinas, float porcentajeGrasas, float porcentajeCarbohidratos) {
        this.id = id;
        this.usuario = usuario;
        this.actividad = actividad;
        this.caloriasRecomendadas = caloriasRecomendadas;
        this.porcentajeProteinas = porcentajeProteinas;
        this.porcentajeGrasas = porcentajeGrasas;
        this.porcentajeCarbohidratos = porcentajeCarbohidratos;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public float getCaloriasRecomendadas() {
        return caloriasRecomendadas;
    }

    public void setCaloriasRecomendadas(float caloriasRecomendadas) {
        this.caloriasRecomendadas = caloriasRecomendadas;
    }

    public float getPorcentajeProteinas() {
        return porcentajeProteinas;
    }

    public void setPorcentajeProteinas(float porcentajeProteinas) {
        this.porcentajeProteinas = porcentajeProteinas;
    }

    public float getPorcentajeGrasas() {
        return porcentajeGrasas;
    }

    public void setPorcentajeGrasas(float porcentajeGrasas) {
        this.porcentajeGrasas = porcentajeGrasas;
    }

    public float getPorcentajeCarbohidratos() {
        return porcentajeCarbohidratos;
    }

    public void setPorcentajeCarbohidratos(float porcentajeCarbohidratos) {
        this.porcentajeCarbohidratos = porcentajeCarbohidratos;
    }
}

   
