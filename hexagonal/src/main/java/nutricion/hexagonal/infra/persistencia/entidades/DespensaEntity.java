package nutricion.hexagonal.infra.persistencia.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DespensaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despensa")
    private Integer id;

    // @Column(name = "id_usuario")   //En vez de id con EntityUsuario
    // private Integer idUsuario;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity usuario;  // Relación con UsuarioEntity

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private ProductoEntity producto;

    private Integer cantidad;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    public DespensaEntity() {}

    public DespensaEntity(ProductoEntity producto, UsuarioEntity idUsuario, Integer cantidad, LocalDate fechaCompra) {
        this.producto = producto;
        this.usuario = idUsuario;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioEntity  getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(UsuarioEntity  idUsuario) {
        this.usuario = idUsuario;
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
