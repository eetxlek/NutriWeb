package nutricion.hexagonal.infra.adaptadores.entrada.dto;

import nutricion.hexagonal.dominio.clases.RecomendacionNutricional;

public class RecomendacionNutricionalDTO {
    private float caloriasRecomendadas;
    private float porcentajeProteinas;
    private float porcentajeGrasas;
    private float porcentajeCarbohidratos;

    public RecomendacionNutricionalDTO(RecomendacionNutricional r) {
        this.caloriasRecomendadas = r.getCaloriasRecomendadas();
        this.porcentajeProteinas = r.getPorcentajeProteinas();
        this.porcentajeGrasas = r.getPorcentajeGrasas();
        this.porcentajeCarbohidratos = r.getPorcentajeCarbohidratos();
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
    
    // Getters y setters si necesitas
}
