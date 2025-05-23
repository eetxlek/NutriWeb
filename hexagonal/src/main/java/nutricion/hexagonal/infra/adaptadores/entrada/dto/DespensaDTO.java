package nutricion.hexagonal.infra.adaptadores.entrada.dto;

import java.time.LocalDateTime;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.infra.persistencia.entidades.DespensaEntity;

public class DespensaDTO {
    private Integer idDespensa;
    private Integer idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private LocalDateTime fechaCompra;

    public DespensaDTO(DespensaEntity entity) {
        this.idDespensa=entity.getId();
        this.idProducto=entity.getProducto().getId();
        this.nombreProducto = entity.getProducto().getNombreProducto();
        this.cantidad = entity.getCantidad();
        this.fechaCompra = entity.getFechaCompra();
    }
    public DespensaDTO(Despensa despensa) {
        this.idDespensa=despensa.getId();
        this.idProducto=despensa.getProducto().getId();
        this.nombreProducto = despensa.getProducto().getNombre();
        this.cantidad = despensa.getCantidad();
        this.fechaCompra = despensa.getFechaCompra();
    }

    public DespensaDTO() {
    }

    public DespensaDTO(Integer idDespensa, Integer idProducto, String nombreProducto, Integer cantidad,
            LocalDateTime fechaCompra) {
        this.idDespensa = idDespensa;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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
    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public Integer getIdDespensa() {
        return idDespensa;
    }
    public void setIdDespensa(Integer idDespensa) {
        this.idDespensa = idDespensa;
    }
    

}

