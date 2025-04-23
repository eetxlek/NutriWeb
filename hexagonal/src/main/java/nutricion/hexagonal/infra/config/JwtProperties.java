package nutricion.hexagonal.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String jwtSecret;
    private long expirationMs;
    public String getJwtSecret() {
        return jwtSecret;
    }
    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }
    public long getExpirationMs() {
        return expirationMs;
    }
    public void setExpirationMs(long expirationMs) {
        this.expirationMs = expirationMs;
    }

    
}

