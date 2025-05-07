package nutricion.hexagonal.dominio.clases;

public class Composicion {
    private Integer idComposicion;
    private Integer idProducto;
    private float calorias;
    private float proteinas;
    private float grasas;
    private float carbohidratos;
    private float fibra;
    private float azucares;
    private float vitaminaC;
    private float potasio;
    private float calcio;
    private float magnesio;
    private float hierro;

    // Constructor con todos los campos
    public Composicion(Integer idComposicion, Integer idProducto, float calorias, float proteinas, float grasas, 
                       float carbohidratos, float fibra, float azucares, float vitaminaC, float potasio, 
                       float calcio, float magnesio, float hierro) {
        this.idComposicion = idComposicion;
        this.idProducto = idProducto;
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
     // Constructor sin el idComposicion
     public Composicion(Integer idProducto, float calorias, float proteinas, float grasas, float carbohidratos) {
        this.idProducto = idProducto;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
    }

    public Composicion() {
    }
    
    public float getFibra() {
        return fibra;
    }
    public void setFibra(float fibra) {
        this.fibra = fibra;
    }
    public float getAzucares() {
        return azucares;
    }
    public void setAzucares(float azucares) {
        this.azucares = azucares;
    }
    public float getVitaminaC() {
        return vitaminaC;
    }
    public void setVitaminaC(float vitaminaC) {
        this.vitaminaC = vitaminaC;
    }
    public float getPotasio() {
        return potasio;
    }
    public void setPotasio(float potasio) {
        this.potasio = potasio;
    }
    public float getCalcio() {
        return calcio;
    }
    public void setCalcio(float calcio) {
        this.calcio = calcio;
    }
    public float getMagnesio() {
        return magnesio;
    }
    public void setMagnesio(float magnesio) {
        this.magnesio = magnesio;
    }
    public float getHierro() {
        return hierro;
    }
    public void setHierro(float hierro) {
        this.hierro = hierro;
    }
    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
  
    public float getCalorias() {
        return calorias;
    }

    public void setCalorias(float calorias) {
        this.calorias = calorias;
    }

    public float getProteinas() {
        return proteinas;
    }

    public void setProteinas(float proteinas) {
        this.proteinas = proteinas;
    }

    public float getGrasas() {
        return grasas;
    }

    public void setGrasas(float grasas) {
        this.grasas = grasas;
    }

    public float getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(float carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public Integer getIdComposicion() {
        return idComposicion;
    }
    public void setIdComposicion(Integer idComposicion) {
        this.idComposicion = idComposicion;
    }

    // Constructor, getters y setters
}
