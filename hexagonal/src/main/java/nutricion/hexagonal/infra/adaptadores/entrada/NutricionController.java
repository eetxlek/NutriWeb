package nutricion.hexagonal.infra.adaptadores.entrada;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.ProductoDespensaDTO;
import nutricion.hexagonal.infra.adaptadores.salida.security.UserPrincipalAdapter;
import nutricion.hexagonal.infra.aplicacion.ProductoService;

@RestController
@RequestMapping("/api")
public class NutricionController {

    private final ProductoService productoService;

    public NutricionController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/despensa")
    public ResponseEntity<String> agregarADespensa(@RequestBody ProductoDespensaDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipalAdapter userPrincipal = (UserPrincipalAdapter) auth.getPrincipal(); // Asegurate de hacer cast
        String usuarioEmail = userPrincipal.getUsername(); //usuario autenticado
        productoService.guardarEnDespensa(dto,usuarioEmail);

        System.out.println("Producto recibido para despensa: " + dto.getNombre());
        return ResponseEntity.ok("Producto guardado en despensa");
    }

    @PostMapping("/consumos")
    public ResponseEntity<String> agregarAConsumos(@RequestBody ProductoDespensaDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipalAdapter userPrincipal = (UserPrincipalAdapter) auth.getPrincipal(); // Asegurate de hacer cast
        String usuarioEmail = userPrincipal.getUsername(); //usuario autenticado
        productoService.guardarEnConsumo(dto,usuarioEmail);
        System.out.println("Producto recibido para consumo: " + dto.getNombre());
        return ResponseEntity.ok("Producto guardado en consumos");
    }

}
