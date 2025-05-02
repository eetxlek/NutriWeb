package nutricion.hexagonal.infra.persistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Composicion_Producto")
public class ComposicionEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_composicion;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;  //modelo o entity?

    private Float calorias;
    private Float proteinas;
    private Float grasas;
    private Float carbohidratos;

    
    public ComposicionEntity(Integer id_composicion, ProductoEntity producto, Float calorias, Float proteinas, Float grasas,
            Float carbohidratos) {
        this.id_composicion = id_composicion;
        this.producto = producto;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
    }
    public ComposicionEntity() {
    }
    public Integer getId_composicion() {
        return id_composicion;
    }
    public void setId_composicion(Integer id_composicion) {
        this.id_composicion = id_composicion;
    }
    public ProductoEntity getProducto() {
        return producto;
    }
    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
    public Float getCalorias() {
        return calorias;
    }
    public void setCalorias(Float calorias) {
        this.calorias = calorias;
    }
    public Float getProteinas() {
        return proteinas;
    }
    public void setProteinas(Float proteinas) {
        this.proteinas = proteinas;
    }
    public Float getGrasas() {
        return grasas;
    }
    public void setGrasas(Float grasas) {
        this.grasas = grasas;
    }
    public Float getCarbohidratos() {
        return carbohidratos;
    }
    public void setCarbohidratos(Float carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    
}
