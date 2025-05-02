package nutricion.hexagonal.infra.seguridad.adaptadores.entrada.dto;

public class ProductoDTO {

    private String nombre;           // nombre_producto en la tabla Productos
    private Double grasa;            // grasas en Composicion_Producto
    private Double carbs;          // carbohidratos en Composicion_Producto
    private Double proteinas;        // proteinas en Composicion_Producto
    private Integer cantidad;      // cantidad en Despensa o Consumo
    private String fechaCompraConsumo;   // fecha_compra o fecha_consumo en formato YYYY-MM-DD
    public ProductoDTO() {
    }
    public ProductoDTO(String nombre, Double grasa, Double carbs, Double proteinas, Integer cantidad,
            String fechaCompraConsumo) {
        this.nombre = nombre;
        this.grasa = grasa;
        this.carbs = carbs;
        this.proteinas = proteinas;
        this.cantidad = cantidad;
        this.fechaCompraConsumo = fechaCompraConsumo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getGrasa() {
        return grasa;
    }
    public void setGrasa(Double grasa) {
        this.grasa = grasa;
    }
    public Double getCarbs() {
        return carbs;
    }
    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }
    public Double getProteinas() {
        return proteinas;
    }
    public void setProteinas(Double proteinas) {
        this.proteinas = proteinas;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getFechaCompraConsumo() {
        return fechaCompraConsumo;
    }
    public void setFechaCompraConsumo(String fechaCompraConsumo) {
        this.fechaCompraConsumo = fechaCompraConsumo;
    }

    
}
