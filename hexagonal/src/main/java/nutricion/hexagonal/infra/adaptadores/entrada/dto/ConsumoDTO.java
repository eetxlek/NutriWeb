package nutricion.hexagonal.infra.adaptadores.entrada.dto;

import java.time.LocalDateTime;
import nutricion.hexagonal.dominio.clases.Consumo;
import nutricion.hexagonal.infra.persistencia.entidades.ConsumoEntity;

//para hacer POST de consumo a rest
public class ConsumoDTO {
    private Integer idConsumo;
    private String nombreProducto;
    private Integer cantidad;
    private LocalDateTime fecha;
    private Float calorias;

    public ConsumoDTO(Consumo consumo) {
        this.nombreProducto = consumo.getProducto().getNombre();
        this.cantidad = consumo.getCantidad();
        this.fecha = consumo.getFechaConsumo();
    }

    public ConsumoDTO(ConsumoEntity entity) {
        this.idConsumo = entity.getId();
        this.nombreProducto = entity.getProducto().getNombreProducto();
        this.cantidad = entity.getCantidad();
        this.fecha = entity.getFechaConsumo();
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Integer idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Float getCalorias() {
        return calorias;
    }

    public void setCalorias(Float calorias) {
        this.calorias = calorias;
    }

}
