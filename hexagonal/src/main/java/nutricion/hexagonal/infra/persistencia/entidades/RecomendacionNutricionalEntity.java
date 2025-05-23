package nutricion.hexagonal.infra.persistencia.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Recomendacion_Nutricional")
public class RecomendacionNutricionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recomendacion")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_actividad", nullable = false)
    private ActividadEntity actividad;

    @Column(name = "calorias_recomendadas")
    private float caloriasRecomendadas;

    @Column(name = "porcentaje_proteinas")
    private float porcentajeProteinas;

    @Column(name = "porcentaje_grasas")
    private float porcentajeGrasas;

    @Column(name = "porcentaje_carbohidratos")
    private float porcentajeCarbohidratos;

    
    public RecomendacionNutricionalEntity() {
    }

    public RecomendacionNutricionalEntity(Integer id, UsuarioEntity usuario, ActividadEntity actividad,
            float caloriasRecomendadas, float porcentajeProteinas, float porcentajeGrasas,
            float porcentajeCarbohidratos) {
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

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ActividadEntity getActividad() {
        return actividad;
    }

    public void setActividad(ActividadEntity actividad) {
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

