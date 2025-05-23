package nutricion.hexagonal.dominio.clases;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Despensa {
    
    private Integer id;
    private Integer idUsuario;
    private Producto producto;
    private Integer cantidad;
    private LocalDateTime fechaCompra;

    public Despensa() {}

    public Despensa(Integer id, Integer idUsuario, Producto producto, Integer cantidad, LocalDateTime fechaCompra) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
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

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
}
