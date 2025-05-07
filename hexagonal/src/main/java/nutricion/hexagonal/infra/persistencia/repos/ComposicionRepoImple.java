package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nutricion.hexagonal.dominio.clases.Composicion;
import nutricion.hexagonal.dominio.interfaces.ComposicionRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;

@Repository
public class ComposicionRepoImple implements ComposicionRepoSalida {

    @Autowired
    private ComposicionProductoRepository jpa;

      public Optional<Composicion> obtenerPorIdProducto(Integer id_Producto) {
        return jpa.findByProducto_Id(id_Producto)
                .map(ComposicionRepoImple::toDomain);
    }

    public void guardar(Composicion composicion) {
        ComposicionEntity entity = toEntity(composicion);
        jpa.save(entity);
    }

    public static  Composicion toDomain(ComposicionEntity entity) {
        return new Composicion(
            entity.getIdComposicion(),
            entity.getProducto().getId_producto(),
            entity.getCalorias() != null ? entity.getCalorias().floatValue() : 0.0f,
            entity.getProteinas() != null ? entity.getProteinas().floatValue() : 0.0f,
            entity.getGrasas() != null ? entity.getGrasas().floatValue() : 0.0f,
            entity.getCarbohidratos() != null ? entity.getCarbohidratos().floatValue() : 0.0f,
            entity.getFibra() != null ? entity.getFibra().floatValue() : 0.0f,
            entity.getAzucares() != null ? entity.getAzucares().floatValue() : 0.0f,
            entity.getVitaminaC() != null ? entity.getVitaminaC().floatValue() : 0.0f,
            entity.getPotasio() != null ? entity.getPotasio().floatValue() : 0.0f,
            entity.getCalcio() != null ? entity.getCalcio().floatValue() : 0.0f,
            entity.getMagnesio() != null ? entity.getMagnesio().floatValue() : 0.0f,
            entity.getHierro() != null ? entity.getHierro().floatValue() : 0.0f
        );
    }

    public static ComposicionEntity toEntity(Composicion composicion) {
        ComposicionEntity entity = new ComposicionEntity();
        entity.setId_composicion(composicion.getIdComposicion());
        
        ProductoEntity producto = new ProductoEntity();
        producto.setId_producto(composicion.getIdProducto());
        entity.setProducto(producto);

        entity.setCalorias(composicion.getCalorias());
        entity.setCarbohidratos(composicion.getCarbohidratos());
        entity.setProteinas(composicion.getProteinas());
        entity.setGrasas(composicion.getGrasas());

        return entity;
    }

    @Override
    public boolean existsByProducto_Id(Integer idProducto) {
        return jpa.existsByProducto_Id(idProducto);
    }


}
