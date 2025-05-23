package nutricion.hexagonal.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import nutricion.hexagonal.infra.adaptadores.salida.security.JwtAuthEntryPointAdapter;
import nutricion.hexagonal.infra.adaptadores.salida.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        private final JwtAuthenticationFilter jwtFilter;
        private final JwtAuthEntryPointAdapter jwtAuthEntryPoint;

        public SecurityConfig(JwtAuthenticationFilter jwtFilter, JwtAuthEntryPointAdapter jwtAuthEntryPoint) {
                this.jwtFilter = jwtFilter;
                this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        }

        @Bean
        SecurityFilterChain securityFilterChain(
                        HttpSecurity http

        ) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF
                                .sessionManagement(sess -> sess
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless
                                                                                                       
                                )
                                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/login", "/despensa", "/consumo", "/registro", "/landing", "/editar-perfil", "/navbar-fragment",
                                                                "/api/auth/login","/productosapi", "/perfil", "/productos", "/api/producto/**", "/api/auth/**", "/error",
                                                                "/auth.js","/navbar-fragment", "/login.js", "/css/**","/favicon.ico", "/js/**",
                                                                "/images/**")
                                                .permitAll() // Permite el acceso sin autenticación a estas rutas
                                                .anyRequest().authenticated() // Requiere autenticación para el resto 
                                )
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(jwtAuthEntryPoint) // Manejo de excepciones de autenticación
                                );

                return http.build();
        }

}