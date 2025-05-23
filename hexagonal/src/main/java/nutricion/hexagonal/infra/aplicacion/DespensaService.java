package nutricion.hexagonal.infra.aplicacion;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import nutricion.hexagonal.dominio.clases.Composicion;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.DeClases.DespensaRepoSalida;
import nutricion.hexagonal.dominio.interfaces.DeClases.ProductoRepoSalida;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.DespensaDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDespensaDTO;
import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;
import nutricion.hexagonal.infra.persistencia.repos.ComposicionProductoRepository;
import nutricion.hexagonal.infra.persistencia.repos.imple.ComposicionRepoImple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DespensaService {
        private static final Logger log = LoggerFactory.getLogger(DespensaService.class);

        private final UsuarioService usuarioService;
        private final DespensaRepoSalida despensaRepo;
        private final ProductoRepoSalida productoRepo;
        private final ComposicionProductoRepository composicionRepo;

        public DespensaService(UsuarioService usuarioService, DespensaRepoSalida despensaRepo,
                        ProductoRepoSalida productoRepo, ComposicionProductoRepository composicionRepo) {
                this.usuarioService = usuarioService;
                this.despensaRepo = despensaRepo;
                this.productoRepo = productoRepo;
                this.composicionRepo = composicionRepo;
        }

        public boolean productoExisteEnDespensa(String correoElectronico, Integer productoId) {
                return despensaRepo.existe(correoElectronico, productoId);
        }

        @Transactional
        public ResponseEntity<String> agregarProductoADespensa(ProductoDespensaDTO dto, String emailUsuario) {
                // Buscar usuario
                Usuario usuario = usuarioService.buscarPorEmail(emailUsuario)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                // Buscar o crear el producto
                Producto producto;
                if (dto.getId() != null) {
                        producto = productoRepo.findById(dto.getId())
                                        .orElseThrow(() -> new RuntimeException("Producto con ID no encontrado"));
                } else {
                        // Buscar por nombre
                        producto = productoRepo.findByNombre(dto.getNombre())
                                        .orElseGet(() -> productoRepo.save(new Producto(null, dto.getNombre())));
                }
                // Verificar y crea composición si no existe
                if (!composicionRepo.existsByProducto_Id(producto.getId())) {
                        Composicion composicion = new Composicion(
                                        null,
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
                // Verificar si el producto ya existe en la despensa del usuario
                Optional<Despensa> despensaExistente = despensaRepo.findByUsuarioCorreoElectronicoAndProductoId(
                                emailUsuario,
                                producto.getId());

                if (despensaExistente.isPresent()) {
                        // Si el producto ya existe, actualizamos la cantidad
                        Despensa despensa = despensaExistente.get();
                        despensa.setCantidad(despensa.getCantidad() + dto.getCantidad()); // Sumar la cantidad

                        despensa.setFechaCompra(dto.getFechaCompraConsumo()); // Actualizar fecha
                        despensaRepo.guardar(despensa); // Guardar los cambios
                        return ResponseEntity.ok("Producto actualizado en la despensa");
                } else {
                        Despensa nuevaDespensa = new Despensa(
                                        null,
                                        usuario.getId(),
                                        producto,
                                        dto.getCantidad(),
                                        dto.getFechaCompraConsumo());
                        despensaRepo.guardar(nuevaDespensa);
                        return ResponseEntity.ok("Producto añadido a la despensa");
                }
        }

        @Transactional //
        public void actualizarProductoEnDespensa(DespensaDTO dto, String usuarioEmail) { // email del user en auth (token)
                log.info("Buscando despensa para email: {}, productoId: {}", usuarioEmail, dto.getIdProducto());
                Despensa despensa = despensaRepo.findById(dto.getIdDespensa())
                                .orElseThrow(() -> new RuntimeException("Entrada en despensa no encontrada"));

                despensa.setCantidad(dto.getCantidad());
                despensa.setFechaCompra(dto.getFechaCompra());

                despensaRepo.guardar(despensa);

        }

        // solo campos de producto nombre cantidad fecha //para cargar en front
        public List<DespensaDTO> obtenerDespensaUsuario(String emailUsuario) {
                Set<Despensa> despensas = despensaRepo.findByUsuarioCorreoElectronico(emailUsuario);
                // Agrupar por ID de producto y quedarse con la última entrada por fechaCompra
                return despensas.stream()
                                .filter(d -> d.getFechaCompra() != null) // <--- evita errores de null
                                .collect(Collectors.groupingBy(
                                                d -> d.getProducto().getId(), // agrupa por producto
                                                Collectors.maxBy(Comparator.comparing(Despensa::getFechaCompra)) // última fecha
                                ))
                                .values().stream()
                                .flatMap(Optional::stream) // extrae el Optional<Despensa>
                                .map(DespensaDTO::new)
                                .collect(Collectors.toList());
        }

        public ProductoDTO toProductoDTO(Producto producto) {
                // Obtener la composición del producto
                Optional<ComposicionEntity> composicionOptional = composicionRepo.findByProducto_Id(producto.getId()); 

                ComposicionEntity composicionEntity = composicionOptional
                                .orElseThrow(() -> new RuntimeException("Composición no encontrada para el producto"));

                return new ProductoDTO(
                                producto.getId(),
                                producto.getNombre(),
                                composicionEntity.getCalorias().doubleValue(), // Convertir Float a Double
                                composicionEntity.getProteinas().doubleValue(), // Convertir Float a Double
                                composicionEntity.getGrasas().doubleValue(), // Convertir Float a Double
                                composicionEntity.getCarbohidratos().doubleValue(), // Convertir Float a Double
                                composicionEntity.getFibra().doubleValue(), // Convertir Float a Double
                                composicionEntity.getAzucares().doubleValue(), // Convertir Float a Double
                                composicionEntity.getVitaminaC().doubleValue(), // Convertir Float a Double
                                composicionEntity.getPotasio().doubleValue(), // Convertir Float a Double
                                composicionEntity.getCalcio().doubleValue(), // Convertir Float a Double
                                composicionEntity.getMagnesio().doubleValue(), // Convertir Float a Double
                                composicionEntity.getHierro().doubleValue()); // Convertir Float a Double
        }

}
