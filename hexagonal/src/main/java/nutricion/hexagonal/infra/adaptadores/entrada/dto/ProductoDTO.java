package nutricion.hexagonal.infra.adaptadores.entrada.dto;

public class ProductoDTO {
    private Integer id;
    private String nombre;
    //private String descripcion;

    private Double calorias;
    private Double proteinas;
    private Double grasas;
    private Double carbohidratos;
    private Double fibra;
    private Double azucares;
    private Double vitaminaC;
    private Double potasio;
    private Double calcio;
    private Double magnesio;
    private Double hierro;

    // Constructor
    public ProductoDTO(Integer id, String nombre,
                       Double calorias, Double proteinas, Double grasas, Double carbohidratos,
                       Double fibra, Double azucares, Double vitaminaC, Double potasio,
                       Double calcio, Double magnesio, Double hierro) {
        this.id = id;
        this.nombre = nombre;
       // this.descripcion = descripcion;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
        this.fibra = fibra;
        this.azucares = azucares;
        this.vitaminaC = vitaminaC;
        this.potasio = potasio;
        this.calcio = calcio;
        this.magnesio = magnesio;
        this.hierro = hierro;
    }

    public ProductoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getGrasas() {
        return grasas;
    }

    public void setGrasas(Double grasas) {
        this.grasas = grasas;
    }

    public Double getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(Double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public Double getProteinas() {
        return proteinas;
    }

    public void setProteinas(Double proteinas) {
        this.proteinas = proteinas;
    }

    public Double getFibra() {
        return fibra;
    }

    public void setFibra(Double fibra) {
        this.fibra = fibra;
    }

    public Double getAzucares() {
        return azucares;
    }

    public void setAzucares(Double azucares) {
        this.azucares = azucares;
    }

    public Double getVitaminaC() {
        return vitaminaC;
    }

    public void setVitaminaC(Double vitaminaC) {
        this.vitaminaC = vitaminaC;
    }

    public Double getPotasio() {
        return potasio;
    }

    public void setPotasio(Double potasio) {
        this.potasio = potasio;
    }

    public Double getCalcio() {
        return calcio;
    }

    public void setCalcio(Double calcio) {
        this.calcio = calcio;
    }

    public Double getMagnesio() {
        return magnesio;
    }

    public void setMagnesio(Double magnesio) {
        this.magnesio = magnesio;
    }

    public Double getHierro() {
        return hierro;
    }

    public void setHierro(Double hierro) {
        this.hierro = hierro;
    }


    // Getters y Setters (puedes usar Lombok si prefieres)
    // @Getter @Setter si usas Lombok
}
