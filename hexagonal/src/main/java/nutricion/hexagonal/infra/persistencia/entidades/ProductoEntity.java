package nutricion.hexagonal.infra.persistencia.entidades;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Productos")
public class ProductoEntity {

     @OneToMany(mappedBy = "producto")
     private Set<ConsumoEntity> consumos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    public ProductoEntity() {
    }

    public ProductoEntity(Integer id_producto, String nombreProducto) {
        this.id = id_producto;
        this.nombreProducto = nombreProducto;

    }

    public ProductoEntity(Set<ConsumoEntity> consumos, Integer id, String nombreProducto) {
        this.consumos = consumos;
        this.id = id;
        this.nombreProducto = nombreProducto;
 
    }

    public Set<ConsumoEntity> getConsumos() {
        return consumos;
    }

    public void setConsumos(Set<ConsumoEntity> consumos) {
        this.consumos = consumos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }



    
}
