package nutricion.hexagonal.infra.seguridad.adaptadores.entrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nutricion.hexagonal.infra.seguridad.adaptadores.entrada.dto.ProductoDTO;
import nutricion.hexagonal.infra.seguridad.adaptadores.salida.ProductoService;

@RestController
@RequestMapping("/api")
public class NutricionController {

     @Autowired
    private ProductoService productoService;

     @PostMapping("/despensa")
    public ResponseEntity<String> agregarADespensa(@RequestBody ProductoDTO dto) {
        int idUsuario = 1; // Reemplaza esto por usuario autenticado
        productoService.guardarEnDespensa(dto, idUsuario);
       
        System.out.println("Producto recibido para despensa: " + dto.getNombre());
        return ResponseEntity.ok("Producto guardado en despensa");
    }

    @PostMapping("/consumos")
    public ResponseEntity<String> agregarAConsumos(@RequestBody ProductoDTO dto) {
        int idUsuario = 1; // Igual que arriba
        productoService.guardarEnConsumo(dto, idUsuario);
        System.out.println("Producto recibido para consumo: " + dto.getNombre());
        return ResponseEntity.ok("Producto guardado en consumos");
    }
    
}
