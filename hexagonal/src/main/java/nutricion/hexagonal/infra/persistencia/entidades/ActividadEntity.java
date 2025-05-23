package nutricion.hexagonal.infra.persistencia.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "actividad")
public class ActividadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Integer id;

    @Column(name = "nivel_actividad", nullable = false)
    private String nivelActividad; // puedes usar Enum si prefieres control estricto

    @Column(name = "factor_actividad", nullable = false)
    private float factorActividad;

    
    public ActividadEntity() {
    }

    public ActividadEntity(Integer id, String nivelActividad, float factorActividad) {
        this.id = id;
        this.nivelActividad = nivelActividad;
        this.factorActividad = factorActividad;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNivelActividad() {
        return nivelActividad;
    }

    public void setNivelActividad(String nivelActividad) {
        this.nivelActividad = nivelActividad;
    }

    public float getFactorActividad() {
        return factorActividad;
    }

    public void setFactorActividad(float factorActividad) {
        this.factorActividad = factorActividad;
    }
}
 
