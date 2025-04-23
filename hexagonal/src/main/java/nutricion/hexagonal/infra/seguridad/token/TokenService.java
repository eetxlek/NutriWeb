package nutricion.hexagonal.infra.seguridad.token;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import nutricion.hexagonal.dominio.interfaces.Token;
import nutricion.hexagonal.infra.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//Tras authservice, este implementa la logica del token. // para seguir patron de servicio podria ser @service en vez de @component.
//implementa interfaz token. Da felxibilidad para implementar otro tipo de token mas adelante.
//genera token con clave secreta cono expiracion, valida token legitimidad, extrae id de token.
//clase servicio manejo de token: crea valida extrae info de token JWT
@Service
public class TokenService implements Token {
    private final JwtProperties jwtProperties;

    public TokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    // genera token usano clave secreta. jwt.jwt-secret=mi-super-clave es la clave
    // de tu servidor apra firmar token JWT. Tambien para validarlo, que no ha sido
    // alterado
    @Override
    public String generarToken(String userId) {
        // uso de jwt.expiration-ms=86400000 = 24h agrega expiracion al token
        try {
            long expirationMs = jwtProperties.getExpirationMs();
            Date now = new Date();
            Date expirationDate = new Date(now.getTime() + expirationMs);

            Key signingKey = getSigningKey(); // ✅ Usa la misma clave secreta configurada // Asegurado en properties que la clave tenga 512 bits (largo enough)
            // genera new cada vez que genera/valida token, no correcto para validar ya existentes. cada token se firma con clave secreta unica. Tu necesitas usar 1 clave misma?
            //si no usas una misma clave, crea clave aleatoria cada vez que no podrá ser validado porque la clave con la que se creo ya no existe. 
            //validarToken() y extraerUserId() fallarían siempre que intenten validar una clave distinta.

            return Jwts.builder()
                    .setSubject(userId)
                    .setIssuedAt(now)
                    .setExpiration(expirationDate)
                    .signWith(signingKey, SignatureAlgorithm.HS512)
                    .compact();
        }
         catch (Exception e) {
            e.printStackTrace(); // Esto ayudará a ver la excepción completa en los logs
            throw new RuntimeException("Error al generar el token", e);
        }
    }

    @Override
    public boolean validarToken(String token) {
        try {
            // Parseamos el token para verificar la firma y la validez
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) //  Usamos la clave fija configurada
                    .build()
                    .parseClaimsJws(token); // Si el token no es válido, se lanzará una excepción

            return true; // Si no hay excepciones, el token es válido
        } catch (ExpiredJwtException e) {
            // El token ha expirado
            System.out.println("Token expirado.");
        } catch (Exception e) {
            // Otros errores generales de JWT
            System.out.println("Token inválido.");
        }
        return false;
    }

    @Override
    public String extraerUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Usamos la clave fija configurada
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // Devuelve el userId del token
    }

    // Eliminar getSigningKey() estaría mal porque rompe la lógica JWT.
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getJwtSecret().getBytes());
        //return Keys.secretKeyFor(SignatureAlgorithm.HS512);    //clave secreta con la longitud adecuada para el algoritmo HS512 // crearia clave new cada vez, no seria validable.
    }
}

// Implementa los demás métodos...
