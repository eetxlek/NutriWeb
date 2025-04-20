package nutricion.hexagonal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig { //usuario en memomria para pruebas con Authmanager

    @Bean
AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("USER", "ADMIN")
        .and()
        .and()
        .build();
}

@Bean
PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}


    // @Bean
    // AuthenticationManager authenticationManager() {
    //     return authentication -> {
    //         throw new UnsupportedOperationException("Autenticación por login tradicional no soportada.");
    //     };
    // }
}

