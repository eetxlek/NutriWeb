package nutricion.hexagonal.infra.aplicacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nutricion.hexagonal.dominio.clases.Composicion;
import nutricion.hexagonal.dominio.clases.Consumo;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.dominio.interfaces.ConsumoRepoSalida;
import nutricion.hexagonal.dominio.interfaces.DespensaRepoSalida;
import nutricion.hexagonal.dominio.interfaces.ProductoRepoSalida;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDespensaDTO;
import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;
import nutricion.hexagonal.infra.persistencia.repos.ComposicionProductoRepository;
import nutricion.hexagonal.infra.persistencia.repos.ComposicionRepoImple;

@Service
public class ProductoService {

    private final ProductoRepoSalida productoRepo;
    private final ComposicionProductoRepository composicionRepo; // repo de infra que extiende JPA
    private final DespensaRepoSalida despensaRepo;
    private final ConsumoRepoSalida consumoRepo;

    public ProductoService(ProductoRepoSalida productoRepo, ComposicionProductoRepository composicionRepo,
            DespensaRepoSalida despensaRepo, ConsumoRepoSalida consumoRepo) {
        this.productoRepo = productoRepo;
        this.composicionRepo = composicionRepo;
        this.despensaRepo = despensaRepo;
        this.consumoRepo = consumoRepo;
    }

    public List<Producto> findByNombreContainingIgnoreCase(String nombre) {
        return productoRepo.findByNombreContainingIgnoreCase(nombre);
    }

    public ComposicionEntity obtenerComposicionPorProductoId(Integer idProducto) {
        return composicionRepo
                .findByProducto_Id(idProducto)
                .orElse(null);
    }

    public Producto guardarSiNoExiste(String nombre) { // FALTA CAMPO DESCRIPCION, la interfaz de dominio de
                                                       // porducto......ñe
        return productoRepo.findByNombre(nombre)
                .orElseGet(() -> {
                    Producto nuevo = new Producto(0, nombre, ""); // o null como id
                    return productoRepo.save(nuevo);
                });
    }

    // SERVICIO SOLO CON OBJETOS DE DOMINIO
    public void guardarEnDespensa(ProductoDespensaDTO dto, int idUsuario) {
        // 1. Buscar o crear el producto
        Producto producto = productoRepo.findByNombre(dto.getNombre())
                .orElseGet(() -> productoRepo.save(new Producto(0, dto.getNombre(), "")));
        // 2. Verificar y guardar composición si no existe
        if (!composicionRepo.existsByProducto_Id(producto.getId())) { // CREA COMPOSICION SI NO EXISTE PRODUCTO
                                                                      // lo crea
            Composicion composicion = new Composicion(
                    0,
                    producto.getId(),
                    dto.getCalorias().floatValue(),
                    dto.getProteinas().floatValue(),
                    dto.getGrasas().floatValue(),
                    dto.getCarbohidratos().floatValue(),
                    dto.getFibra().floatValue(),
                    dto.getAzucares().floatValue(),
                    dto.getVitaminaC().floatValue(),
                    dto.getPotasio().floatValue(),
                    dto.getCalcio().floatValue(),
                    dto.getMagnesio().floatValue(),
                    dto.getHierro().floatValue());

            ComposicionEntity entity = ComposicionRepoImple.toEntity(composicion);

            composicionRepo.save(entity);
        }
        // 3. Buscar si el producto ya está en la despensa del usuario
        Optional<Despensa> existente = despensaRepo.findDespensaByUsuarioIdAndProductoId(idUsuario, producto.getId());
        if (existente.isPresent()) {
            Despensa despensaExistente = existente.get();
            despensaExistente.setCantidad(despensaExistente.getCantidad() + dto.getCantidad());
            despensaRepo.guardar(despensaExistente); // dominio
        } else {
            Despensa nuevaDespensa = dto.toDespensa(producto);
            despensaRepo.guardar(nuevaDespensa);
        }
    }

    public void guardarEnConsumo(ProductoDespensaDTO dto, int idUsuario) {
        Producto producto = productoRepo.findByNombre(dto.getNombre())
                .orElseThrow(() -> new RuntimeException("El producto no existe"));

        Consumo consumo = new Consumo(producto, idUsuario, dto.getCantidad(),
                LocalDate.parse(dto.getFechaCompraConsumo()));
        consumoRepo.guardar(consumo);
    }
}
