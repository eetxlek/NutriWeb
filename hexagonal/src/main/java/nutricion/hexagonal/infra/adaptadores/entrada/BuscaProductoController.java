package nutricion.hexagonal.infra.adaptadores.entrada;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDTO;
import nutricion.hexagonal.infra.aplicacion.ProductoService;
import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;

@RestController
@RequestMapping("/api")
public class BuscaProductoController {

    private final ProductoService productoService;

    public BuscaProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/producto") // para coincidir con el frontend
    public ResponseEntity<List<ProductoDTO>> buscarProductos(@RequestParam String nombre) {

        if (nombre == null || nombre.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // HTTP 400 Bad Request
        }
        try {
            System.out.println("🔍 Buscando productos que contengan: " + nombre);
            List<Producto> productos = productoService.findByNombreContainingIgnoreCase(nombre);
            System.out.println("✅ Cantidad encontrada: " + productos.size());
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build(); // HTTP 204
            }
            List<ProductoDTO> dtoList = new ArrayList<>();

            for (Producto producto : productos) {
                ComposicionEntity composicion = productoService.obtenerComposicionPorProductoId(producto.getId());
                System.out.println("🧪 Producto: " + producto.getNombre() + " -> composición: " + (composicion != null));
                if (composicion != null) {
                    ProductoDTO dto = new ProductoDTO(
                            producto.getId(),
                            producto.getNombre(),
                            safeDouble(composicion.getCalorias()),
                            safeDouble(composicion.getProteinas()),
                            safeDouble(composicion.getGrasas()),
                            safeDouble(composicion.getCarbohidratos()),
                            safeDouble(composicion.getFibra()),
                            safeDouble(composicion.getAzucares()),
                            safeDouble(composicion.getVitaminaC()),
                            safeDouble(composicion.getPotasio()),
                            safeDouble(composicion.getCalcio()),
                            safeDouble(composicion.getMagnesio()),
                            safeDouble(composicion.getHierro()));
                    dtoList.add(dto);
                }
            }
            return ResponseEntity.ok(dtoList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // HTTP 500
        }
    }

    private double safeDouble(Number valor) {
        return valor != null ? valor.doubleValue() : 0.0;
    }
}
