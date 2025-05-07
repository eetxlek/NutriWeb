package nutricion.hexagonal.dominio.clases;

import java.time.LocalDate;


public class Consumo {

    private Integer id;
    private Integer idUsuario;
    private Producto producto;
    private Integer cantidad;
    private LocalDate fechaConsumo;
    public Consumo() {}

    public Consumo(Producto producto, Integer idUsuario, Integer cantidad, LocalDate fechaConsumo) {
        this.producto = producto;
        this.idUsuario = idUsuario;
        this.cantidad = cantidad;
        this.fechaConsumo = fechaConsumo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(LocalDate fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }
    
}
