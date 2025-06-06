package nutricion.hexagonal.infra.persistencia.entidades;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consumo") 
public class ConsumoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumo")
    private Integer id;

    // @Column(name = "id_usuario")
    // private Integer idUsuario;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuario;  // Relación con UsuarioEntity

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private ProductoEntity producto; // Relación con ProductoEntity

    private Integer cantidad;

    @Column(name = "fecha_consumo")
    private LocalDateTime fechaConsumo;

    public ConsumoEntity() {}

    public ConsumoEntity(ProductoEntity producto, UsuarioEntity usuario, Integer cantidad, LocalDateTime fechaConsumo) {
        this.producto = producto;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.fechaConsumo = fechaConsumo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioEntity getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
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

    public LocalDateTime getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(LocalDateTime fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    // Getters y setters...
}

    
