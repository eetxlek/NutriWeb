package nutricion.hexagonal.infra.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        JwtAuthenticationFilter jwtFilter
    ) throws Exception {
        http
            .cors(withDefaults())  // Habilita CORS con configuración por defecto
            .csrf(AbstractHttpConfigurer::disable)  // Desactiva CSRF (no necesario para APIs stateless)
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // ¡Importante para JWT porque JWT es stateless por diseño. Sino spring sec intenta crear sesion de forma innecesaria
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers( "/","/login","/landing", "/api/auth/**").permitAll()  // Permite registro/login
                .anyRequest().authenticated()  // El resto requieren autenticación
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  // Filtro JWT

        return http.build();
    }
}