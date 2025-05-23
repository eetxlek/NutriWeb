package nutricion.hexagonal.infra.adaptadores.entrada.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.dominio.clases.Producto;

public class ProductoDespensaDTO extends ProductoDTO {
    private Integer cantidad;
    private LocalDateTime fechaCompraConsumo;
    @JsonIgnore
    private Integer idUsuario;
    

     public ProductoDespensaDTO(Integer id, String nombre, String descripcion, Double calorias, Double proteinas,
                               Double grasas, Double carbohidratos, Double fibra, Double azucares, Double vitaminaC,
                               Double potasio, Double calcio, Double magnesio, Double hierro, Integer cantidad,
                               LocalDateTime fechaCompraConsumo) {
        super(id, nombre, calorias, proteinas, grasas, carbohidratos, fibra, azucares, vitaminaC, 
              potasio, calcio, magnesio, hierro); // Llamada al constructor de ProductoDTO
        this.cantidad = cantidad;
        this.fechaCompraConsumo = fechaCompraConsumo;
    }
    public ProductoDespensaDTO() {
    }

    
    
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public LocalDateTime getFechaCompraConsumo() {
        return fechaCompraConsumo;
    }
    public void setFechaCompraConsumo(LocalDateTime fechaCompraConsumo) {
        this.fechaCompraConsumo = fechaCompraConsumo;
    }

     public Despensa toDespensa(Producto producto) {

        return new Despensa(
            null, // autoincrementado por la base de datos
            this.idUsuario,
            producto,
            this.cantidad,
            this.fechaCompraConsumo
        );
    }
     public Integer getIdUsuario() {
         return idUsuario;
     }
     public void setIdUsuario(Integer idUsuario) {
         this.idUsuario = idUsuario;
     }
     @Override
     public String toString() {
        return "ProductoDespensaDTO [cantidad=" + cantidad + ", fechaCompraConsumo=" + fechaCompraConsumo
                + ", idUsuario=" + idUsuario + ", getId()=" + getId() + "]";
     }
    
     
}

