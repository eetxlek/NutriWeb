package nutricion.hexagonal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nutricion.hexagonal.infra.seguridad.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // private final AuthenticationManager authenticationManager;
    // private final JwtTokenProvider jwtTokenProvider;

    // public AuthController(AuthenticationManager authenticationManager,
    //                       JwtTokenProvider jwtTokenProvider) {
    //     this.authenticationManager = authenticationManager;
    //     this.jwtTokenProvider = jwtTokenProvider;
    // }

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    //     try {
    //         Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    //         );

    //         String token = jwtTokenProvider.generarToken(authentication.getName());  //que provider funcione como esperas

    //         return ResponseEntity.ok(new JwtResponse(token));
    //     } catch (AuthenticationException e) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    //     }
    // } CON 1,ATUHMANAGER Y 2.ENDPOINT API/AUTH Y 3.JWTrESPONSE Y 4. LOGINREQUEST
    /*  un POST a http://localhost:8080/api/auth/login con { "username": "admin",  "password": "admin"} deveulve {  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."}
}*/
}

