package nutricion.hexagonal.infra.adaptadores.entrada;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nutricion.hexagonal.dominio.clases.Consumo;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ConsumoDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.DespensaDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDespensaDTO;
import nutricion.hexagonal.infra.adaptadores.salida.security.UserPrincipalAdapter;
import nutricion.hexagonal.infra.aplicacion.DespensaService;
import nutricion.hexagonal.infra.aplicacion.ProductoService;
import nutricion.hexagonal.infra.aplicacion.UsuarioService;
import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;

@RestController
@RequestMapping("/api")
public class NutricionController {

    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final DespensaService despensaService;

    public NutricionController(ProductoService productoService, UsuarioService usuarioService,
            DespensaService despensaService) {
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.despensaService = despensaService;
    }

    // Estás accediendo directamente al contexto de seguridad estático (SecurityContextHolder) dentro del método,
    // lo cual acopla más tu lógica de negocio al framework de seguridad. Ahora YA NO Spring inyecta automaticamente usuario autenticado.

    @GetMapping("/despensa/existe")
    public ResponseEntity<Boolean> productoExisteEnDespensa(@RequestParam Integer productoId,
            @AuthenticationPrincipal UserPrincipalAdapter userPrincipal) {
        String correoElectronico = userPrincipal.getUsername();
        boolean existe = despensaService.productoExisteEnDespensa(correoElectronico, productoId);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/usuario/despensa")
    public ResponseEntity<List<DespensaDTO>> obtenerDespensaUsuario(
            @AuthenticationPrincipal UserPrincipalAdapter userPrincipal) {
        String email = userPrincipal.getUsername();
        List<DespensaDTO> despensa = despensaService.obtenerDespensaUsuario(email);
        return ResponseEntity.ok(despensa);
    }

    @PostMapping("/despensa")
    public ResponseEntity<String> agregarADespensa(
            @RequestBody ProductoDespensaDTO dto,
            @AuthenticationPrincipal UserPrincipalAdapter userPrincipal) {
        try {
            String usuarioEmail = userPrincipal.getUsername();
            despensaService.agregarProductoADespensa(dto, usuarioEmail);
            System.out.println("Producto agregado a la despensa: " + dto.getNombre());
            return ResponseEntity.ok("Producto agregado a la despensa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al guardar el producto en la despensa: " + e.getMessage());
        }
    }

    @PutMapping("/despensa")
    public ResponseEntity<String> actualizarDespensa(
            @RequestBody DespensaDTO dto,
            @AuthenticationPrincipal UserPrincipalAdapter userPrincipal) {
        try {
            String usuarioEmail = userPrincipal.getUsername();
            despensaService.actualizarProductoEnDespensa(dto, usuarioEmail);
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el producto en la despensa: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/consumos")
    public ResponseEntity<List<ConsumoDTO>> obtenerConsumosUsuario(
            @AuthenticationPrincipal UserPrincipalAdapter userPrincipal) {
        String email = userPrincipal.getUsername();
        Set<Consumo> consumos = usuarioService.obtenerConsumos(email);
        List<ConsumoDTO> respuesta = consumos.stream()
                .map(consumo -> {
                    ConsumoDTO dto = new ConsumoDTO(consumo);
                    Integer idProducto = consumo.getProducto().getId();
                    ComposicionEntity composicion = productoService.obtenerComposicionPorProductoId(idProducto);

                    if (composicion != null) {
                        Float calorias = composicion.getCalorias();
                        Integer cantidad = consumo.getCantidad();

                        // Calculamos calorías proporcionales si es necesario
                        Float caloriasTotales = (calorias != null && cantidad != null)
                                ? (calorias * cantidad) / 100f
                                : null;

                        dto.setCalorias(caloriasTotales); //envia al front cada registro de consumo con campos de DTOConsumo
                    }

                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/consumos")
    public ResponseEntity<String> agregarAConsumos(
            @RequestBody ProductoDespensaDTO dto,
            @AuthenticationPrincipal UserPrincipalAdapter userPrincipal) {
        try {
            String usuarioEmail = userPrincipal.getUsername();
            productoService.guardarEnConsumo(dto, usuarioEmail);
            System.out.println("Producto recibido para consumo: " + dto.getNombre());
            return ResponseEntity.ok("Producto guardado en consumos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al guardar el producto en consumos: " + e.getMessage());
        }
    }

}
