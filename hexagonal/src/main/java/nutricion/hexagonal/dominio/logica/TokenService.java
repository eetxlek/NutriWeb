package nutricion.hexagonal.dominio.logica;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import nutricion.hexagonal.config.JwtProperties;
import nutricion.hexagonal.dominio.interfaces.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenService implements Token {
    private final JwtProperties jwtProperties;

    public TokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
    //genera token usano clave secreta. jwt.jwt-secret=mi-super-clave es la clave de tu servidor apra firmar token JWT. Tambien para validarlo, que no ha sido alterado
    @Override
    public String generarToken(String userId) {
        //uso de jwt.expiration-ms=86400000 = 24h agrega expiracion al token
        long expirationMs = jwtProperties.getExpirationMs();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean validarToken(String token) {
        try {
            // Parseamos el token para verificar la firma y la validez
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
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
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // Devuelve el userId del token
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getJwtSecret().getBytes());
    }
}

// Implementa los demás métodos...
