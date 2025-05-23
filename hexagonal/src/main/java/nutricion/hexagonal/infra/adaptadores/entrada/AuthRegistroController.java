package nutricion.hexagonal.infra.adaptadores.entrada;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.JwtResponseDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.LoginRequestDTO;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.RegistroRequestDTO;
import nutricion.hexagonal.infra.adaptadores.salida.security.JwtTokenProvider;
import nutricion.hexagonal.infra.aplicacion.UsuarioService;

//clasico adaptador de entrada, recibe, delega y responde. Acoplado a authmanager de springsec y a jwttokenprovider (servicio de tokens)
@RestController
@RequestMapping("/api/auth")
public class AuthRegistroController {
    private final UsuarioService usuarioService;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthRegistroController(UsuarioService usuarioService,
            JwtTokenProvider jwtTokenProvider) {
        this.usuarioService = usuarioService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Autenticar y devolver JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) { // email y pasword
        System.out.println("Intentando login con: " + request.getEmail());

        // Verificar las credenciales del usuario
        if (!usuarioService.verificarLogin(request.getEmail(), request.getPassword())) { 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        // Generar el token JWT si las credenciales son correctas
        String token = jwtTokenProvider.generarToken(request.getEmail());

        // Crear la respuesta con token
        JwtResponseDTO response = new JwtResponseDTO(token, request.getEmail());
        return ResponseEntity.ok(response); // El token y el email se envían correctamente
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody RegistroRequestDTO request) {
        if (usuarioService.existeEmail(request.getCorreoElectronico())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está en uso");
        }
      
        // recibe DTO genera un user y encripta en el metodo registrar
        usuarioService.registrarUsuario(request); // debe encriptar la contraseña internamente lo hace el servicio antes 
        usuarioService.registrarRecomendacion(request);                                   
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
    }

}
