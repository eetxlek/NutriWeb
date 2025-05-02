package nutricion.hexagonal.infra.persistencia.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Despensa")
public class DespensaEntity {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_despensa;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    private Integer cantidad;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    public DespensaEntity() {
    }

    public DespensaEntity(Integer id_despensa, Integer idUsuario, ProductoEntity producto, Integer cantidad,
            LocalDate fechaCompra) {
        this.id_despensa = id_despensa;
        this.idUsuario = idUsuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    public Integer getId_despensa() {
        return id_despensa;
    }

    public void setId_despensa(Integer id_despensa) {
        this.id_despensa = id_despensa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }


    
    // Getters, setters
}
