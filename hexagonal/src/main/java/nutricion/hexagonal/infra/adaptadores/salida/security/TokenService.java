package nutricion.hexagonal.infra.adaptadores.salida.security;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;
import nutricion.hexagonal.dominio.interfaces.Token;
import nutricion.hexagonal.infra.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

//Tras authservice, este implementa la logica del token. 
//implementa interfaz token. Da felxibilidad para implementar otro tipo de token mas adelante.
//genera token con clave secreta Y expiracion, valida token legitimidad, extrae id/email de token.
//clase servicio manejo de token: crea valida extrae info de token JWT
@Service
public class TokenService implements Token {
    
    private final JwtProperties jwtProperties;

    public TokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    // genera token usando clave secreta. jwt.jwt-secret=mi-super-clave es la clave de tu servidor apra firmar token JWT. 
    // tambien para validarlo, que no haya sido alterado
    @Override
    public String generarToken(String email) { // el controller pasa usuario tokenProvider.generarToken(usuario);
                                                // uso de jwt.expiration-ms=86400000 = 24h agrega expiracion al token
        System.out.println("Generaando token");
        try {
            long expirationMs = jwtProperties.getExpirationMs();
            Date now = new Date();
            Date expirationDate = new Date(now.getTime() + expirationMs);

            Key signingKey = getSigningKey(); // Usa la misma clave secreta configurada
            System.out.println("TOKEN GENERADO ES: "+Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(now)
                    .setExpiration(expirationDate)
                    .signWith(signingKey, SignatureAlgorithm.HS512)
                    .compact().toString());
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(now)
                    .setExpiration(expirationDate)
                    .signWith(signingKey, SignatureAlgorithm.HS512)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace(); // Esto ayudará a ver la excepción completa en los logs
            throw new RuntimeException("Error al generar el token", e);
        }
    }

    @Override
    public boolean validarToken(String token) {
        System.out.println("Validando token");
        try {
            // Parseamos el token para verificar la firma y la validez
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Usamos la clave fija configurada
                    .build()
                    .parseClaimsJws(token); // Si el token no es válido, se lanzará una excepción

            return true; // Si no hay excepciones, el token es válido
        } catch (ExpiredJwtException e) {
            // El token ha expirado
            System.out.println("Token expirado. Expiración: " + e.getClaims().getExpiration());
            return false; // Retorna false, ya que el token ha expirado
        } catch (MalformedJwtException e) {
            // El token tiene un formato incorrecto
            System.out.println("Token inválido: Formato incorrecto.");
        } catch (UnsupportedJwtException e) {
            // El token no es compatible
            System.out.println("Token inválido: Tipo de token no soportado.");
        } catch (IllegalArgumentException e) {
            // El token es nulo o vacío
            System.out.println("Token inválido: Argumento nulo o vacío.");
        } catch (Exception e) {
            // Otros errores generales
            System.out.println("Error al validar el token: " + e.getMessage());
        }
        return false; // Si se llega aquí, es porque hubo algún error en la validación
    }

    @Override
    public String extraerEmail(String token) {
        System.out.println("En extraccion email de token");

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Usamos la clave fija configurada
                .build()
                .parseClaimsJws(token) 
                .getBody();
        return claims.getSubject(); // Devuelve el userId email del token
    }

    // es parte de la lógica JWT. Hace referencia a clave (secreto) de firma: jwt.jwt-secret
    private Key getSigningKey() {
        String secret = jwtProperties.getJwtSecret();
        System.out.println("Clave secreta leída: " + secret);
        return Keys.hmacShaKeyFor(secret.getBytes()); // usa el algoritmo HMAC-SHA
    }
}

