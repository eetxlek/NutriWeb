package nutricion.hexagonal.dominio.clases;


public class Actividad {

    private Integer id;
    private String nivelActividad; 
    private float factorActividad;
    public Actividad() {
    }
    public Actividad(Integer id, String nivelActividad, float factorActividad) {
        this.id = id;
        this.nivelActividad = nivelActividad;
        this.factorActividad = factorActividad;
    }
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
