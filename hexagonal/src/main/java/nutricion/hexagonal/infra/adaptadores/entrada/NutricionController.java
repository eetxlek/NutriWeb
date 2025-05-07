package nutricion.hexagonal.infra.adaptadores.entrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDespensaDTO;
import nutricion.hexagonal.infra.aplicacion.ProductoService;

@RestController
@RequestMapping("/api")
public class NutricionController {

     @Autowired
    private ProductoService productoService;

     @PostMapping("/despensa")
    public ResponseEntity<String> agregarADespensa(@RequestBody ProductoDespensaDTO dto) {
        int idUsuario = 1; // Reemplaza esto por usuario autenticado
        productoService.guardarEnDespensa(dto, idUsuario);
       
        System.out.println("Producto recibido para despensa: " + dto.getNombre());
        return ResponseEntity.ok("Producto guardado en despensa");
    }

    @PostMapping("/consumos")
    public ResponseEntity<String> agregarAConsumos(@RequestBody ProductoDespensaDTO dto) {
        int idUsuario = 1; // Igual que arriba
        productoService.guardarEnConsumo(dto, idUsuario);
        System.out.println("Producto recibido para consumo: " + dto.getNombre());
        return ResponseEntity.ok("Producto guardado en consumos");
    }
    
}
