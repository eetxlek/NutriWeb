package nutricion.hexagonal.infra.persistencia.entidades;

import jakarta.persistence.Column;
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
     @Column(name = "id_composicion")
    private Integer idComposicion;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private ProductoEntity producto;

    private Float calorias;
    private Float proteinas;
    private Float grasas;
    private Float carbohidratos;
    private Float fibra;
    private Float azucares;
    @Column(name = "vitamina_c")
    private Float vitaminaC;
    private Float potasio;
    private Float calcio;
    private Float magnesio;
    private Float hierro;

    
    public ComposicionEntity(Integer id_composicion, ProductoEntity producto, Float calorias, Float proteinas, Float grasas,
            Float carbohidratos) {
        this.idComposicion = id_composicion;
        this.producto = producto;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
    }
    
    public ComposicionEntity(Integer idComposicion, ProductoEntity producto, Float calorias, Float proteinas,
            Float grasas, Float carbohidratos, Float fibra, Float azucares, Float vitaminaC, Float potasio,
            Float calcio, Float magnesio, Float hierro) {
        this.idComposicion = idComposicion;
        this.producto = producto;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
        this.fibra = fibra;
        this.azucares = azucares;
        this.vitaminaC = vitaminaC;
        this.potasio = potasio;
        this.calcio = calcio;
        this.magnesio = magnesio;
        this.hierro = hierro;
    }

    public ComposicionEntity() {
    }
    public Integer getId_composicion() {
        return idComposicion;
    }
    public void setId_composicion(Integer id_composicion) {
        this.idComposicion = id_composicion;
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

    public Integer getIdComposicion() {
        return idComposicion;
    }

    public void setIdComposicion(Integer idComposicion) {
        this.idComposicion = idComposicion;
    }

    public Float getFibra() {
        return fibra;
    }

    public void setFibra(Float fibra) {
        this.fibra = fibra;
    }

    public Float getAzucares() {
        return azucares;
    }

    public void setAzucares(Float azucares) {
        this.azucares = azucares;
    }

    public Float getVitaminaC() {
        return vitaminaC;
    }

    public void setVitaminaC(Float vitaminaC) {
        this.vitaminaC = vitaminaC;
    }

    public Float getPotasio() {
        return potasio;
    }

    public void setPotasio(Float potasio) {
        this.potasio = potasio;
    }

    public Float getCalcio() {
        return calcio;
    }

    public void setCalcio(Float calcio) {
        this.calcio = calcio;
    }

    public Float getMagnesio() {
        return magnesio;
    }

    public void setMagnesio(Float magnesio) {
        this.magnesio = magnesio;
    }

    public Float getHierro() {
        return hierro;
    }

    public void setHierro(Float hierro) {
        this.hierro = hierro;
    }

    
}
