package nutricion.hexagonal.infra.seguridad.adaptadores.entrada;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nutricion.hexagonal.dominio.UsuarioService;
import nutricion.hexagonal.infra.seguridad.adaptadores.UsuarioServiceAdapter;
import nutricion.hexagonal.infra.seguridad.adaptadores.entrada.dto.JwtResponseDTO;
import nutricion.hexagonal.infra.seguridad.adaptadores.entrada.dto.LoginRequestDTO;
import nutricion.hexagonal.infra.seguridad.token.JwtTokenProvider;

//clasico adaptador de entrada, recibe, delega y responde. Acoplado a authmanager de springsec y a jwttokenprovider (servicio de tokens)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioServiceAdapter usuarioService;
 
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UsuarioServiceAdapter usuarioService,
            JwtTokenProvider jwtTokenProvider) {
        this.usuarioService = usuarioService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Autenticar y devolver JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) { // email y pasword
        System.out.println("Intentando login con: " + request.getEmail());

        // Verificar las credenciales del usuario
        if (!usuarioService.verificarLogin(request.getEmail(), request.getPassword())) { // mejor que
                                                                                         // authenticaionmanager.authenticate
                                                                                         // : control completo para
                                                                                         // comparar contraseña con
                                                                                         // encriptacion en BD
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        // Generar el token JWT si las credenciales son correctas
        String token = jwtTokenProvider.generarToken(request.getEmail());

        // Crear la respuesta con token
        JwtResponseDTO response = new JwtResponseDTO(token, request.getEmail());
        return ResponseEntity.ok(response); // El token y el email se envían correctamente
    }
    // try {
    // Authentication authentication = usuarioService.authenticate(
    // new UsernamePasswordAuthenticationToken(request.getEmail(),
    // request.getPassword()));

    // System.out.println("Autenticación exitosa para: " + request.getEmail()); //
    // esto no daria un error 500 sino 4

    // String token = jwtTokenProvider.generarToken(authentication.getName()); // el
    // email. que provider funcione
    // // como
    // // esperas

    // return ResponseEntity.ok(new JwtResponseDTO(token,
    // authentication.getName())); // se entiende email?
    // } catch (BadCredentialsException e) {
    // e.printStackTrace();
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales
    // inválidas");
    // }

    // 1,ATUHMANAGER Y 2.ENDPOINT API/AUTH Y 3.JWTrESPONSE Y 4. LOGINREQUEST
    /*
     * un POST a http://localhost:8080/api/auth/login con { "username": "admin",
     * "password": "admin"} deveulve { "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."}
     * }
     */
}
