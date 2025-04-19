package nutricion.hexagonal.infra.seguridad;

import java.security.Key;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import nutricion.hexagonal.config.JwtProperties;
import nutricion.hexagonal.dominio.interfaces.Token;

@Component
public class JwtTokenAdapter implements Token {

    private final JwtProperties jwtProperties;

    public JwtTokenAdapter(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String generarToken(String userId) {
        return Jwts.builder()
            .setSubject(userId)
            .signWith(getSigningKey(), SignatureAlgorithm.HS512)
            .compact();
    }

    @Override
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String extraerUserId(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
    private Key getSigningKey() {
    return Keys.hmacShaKeyFor(jwtProperties.getJwtSecret().getBytes());
}
}
