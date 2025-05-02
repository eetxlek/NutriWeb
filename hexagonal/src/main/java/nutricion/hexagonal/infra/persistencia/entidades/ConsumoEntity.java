package nutricion.hexagonal.infra.persistencia.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "Consumo")
public class ConsumoEntity {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_despensa;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    private Integer cantidad;

    @Column(name = "fecha_consumo")
    private LocalDate fechaConsumo;

    public ConsumoEntity() {
    }

    public ConsumoEntity(Integer id_despensa, Integer idUsuario, ProductoEntity producto, Integer cantidad,
            LocalDate fechaConsumo) {
        this.id_despensa = id_despensa;
        this.idUsuario = idUsuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaConsumo = fechaConsumo;
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

    public LocalDate getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(LocalDate fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    

}
