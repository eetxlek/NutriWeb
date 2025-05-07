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
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/producto") // para coincidir con el frontend
    public ResponseEntity<List<ProductoDTO>> buscarProductos(@RequestParam String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // HTTP 400 Bad Request
        }
        try {
            List<Producto> productos = productoService.findByNombreContainingIgnoreCase(nombre);

            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build(); // HTTP 204
            }
            List<ProductoDTO> dtoList = new ArrayList<>();

            for (Producto producto : productos) {
                ComposicionEntity composicion = productoService.obtenerComposicionPorProductoId(producto.getId());

                if (composicion != null) {
                    ProductoDTO dto = new ProductoDTO(
                            producto.getId(),
                            producto.getNombre(),
                            producto.getDescripcion(),
                            (double) composicion.getCalorias(),
                            (double) composicion.getProteinas(),
                            (double) composicion.getGrasas(),
                            (double) composicion.getCarbohidratos(),
                            (double) composicion.getFibra(),
                            (double) composicion.getAzucares(),
                            (double) composicion.getVitaminaC(),
                            (double) composicion.getPotasio(),
                            (double) composicion.getCalcio(),
                            (double) composicion.getMagnesio(),
                            (double) composicion.getHierro());
                    dtoList.add(dto);
                }
            }
            return ResponseEntity.ok(dtoList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // HTTP 500
        }
    }
}
