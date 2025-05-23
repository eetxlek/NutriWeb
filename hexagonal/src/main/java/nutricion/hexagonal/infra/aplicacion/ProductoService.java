package nutricion.hexagonal.infra.aplicacion;

import java.util.List;
import org.springframework.stereotype.Service;
import nutricion.hexagonal.dominio.clases.Consumo;
import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.DeClases.ProductoRepoSalida;
import nutricion.hexagonal.dominio.interfaces.DeClases.ConsumoRepoSalida;
import nutricion.hexagonal.dominio.interfaces.DeClases.DespensaRepoSalida;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDespensaDTO;
import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;
import nutricion.hexagonal.infra.persistencia.repos.ComposicionProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//servicio no conoce implementaciones. solo interfaz dominio. No sabe nada de JPA  //metodos interactuan con interfaces de dominio
@Service
public class ProductoService {
         private static final Logger log = LoggerFactory.getLogger(ProductoService.class);
    // interfaces de dominio
    private final UsuarioService usuarioService; 
    private final ProductoRepoSalida productoRepo;
    private final ComposicionProductoRepository composicionRepo; 
    private final ConsumoRepoSalida consumoRepo;

    public ProductoService(UsuarioService usuarioService, ProductoRepoSalida productoRepo,
            ComposicionProductoRepository composicionRepo,
            DespensaRepoSalida despensaRepo, ConsumoRepoSalida consumoRepo) {
        this.usuarioService = usuarioService;
        this.productoRepo = productoRepo;
        this.composicionRepo = composicionRepo;
        this.consumoRepo = consumoRepo;
    }

    public List<Producto> findByNombreContainingIgnoreCase(String nombre) {
        return productoRepo.findByNombreContainingIgnoreCase(nombre);
    }
    //esto está acoplado...
    public ComposicionEntity obtenerComposicionPorProductoId(Integer idProducto) {
        return composicionRepo
                .findByProducto_Id(idProducto)
                .orElse(null);
    }

    // NO SE USA
    public Producto guardarSiNoExiste(String nombre) { 
        return productoRepo.findByNombre(nombre)
                .orElseGet(() -> {
                    Producto nuevo = new Producto(null, nombre); // o null como id
                    return productoRepo.save(nuevo);
                });
    }

    public void guardarEnConsumo(ProductoDespensaDTO dto, String usuarioEmail) {

        Producto producto = productoRepo.findByNombre(dto.getNombre())
                .orElseThrow(() -> new RuntimeException("El producto no existe"));
       
        // Obtener el usuario desde el servicio
        Usuario usuario = usuarioService.buscarPorEmail(usuarioEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " +usuarioEmail));

        int id = usuario.getId();
        Consumo consumo = new Consumo(producto, id, dto.getCantidad(),
                dto.getFechaCompraConsumo());
        consumoRepo.guardar(consumo);
    }

    public ProductoDTO toProductoDTO(ProductoEntity productoEntity) {
        // Obtener la composición asociada al producto
        ComposicionEntity composicionEntity = composicionRepo.findByProducto_Id(productoEntity.getId())
                .orElseThrow(() -> new RuntimeException("Composición no encontrada para el producto"));

        // Convertir a ProductoDTO incluyendo la información de la composición
        return new ProductoDTO(
                productoEntity.getId(),
                productoEntity.getNombreProducto(),
                (double) composicionEntity.getCalorias(), // Convertir float a Double
                (double) composicionEntity.getProteinas(), // Convertir float a Double
                (double) composicionEntity.getGrasas(), // Convertir float a Double
                (double) composicionEntity.getCarbohidratos(), // Convertir float a Double
                (double) composicionEntity.getFibra(), // Convertir float a Double
                (double) composicionEntity.getAzucares(), // Convertir float a Double
                (double) composicionEntity.getVitaminaC(), // Convertir float a Double
                (double) composicionEntity.getPotasio(), // Convertir float a Double
                (double) composicionEntity.getCalcio(), // Convertir float a Double
                (double) composicionEntity.getMagnesio(), // Convertir float a Double
                (double) composicionEntity.getHierro() // Convertir float a Double
        );
    }

     public Producto obtenerProductoPorId(Integer id) {
        // Implementa tu lógica para buscar por ID
        return productoRepo.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
