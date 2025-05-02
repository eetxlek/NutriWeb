package nutricion.hexagonal.infra.seguridad.adaptadores.salida;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import nutricion.hexagonal.dominio.interfaces.PasswordEncrypter;

@Component
public class BcryptPasswordEncrypter implements PasswordEncrypter {
    //private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String rawPassword) {
        //return encoder.encode(rawPassword);
        return rawPassword; // no hace nada en modo prueba
    }
    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
       // return encoder.matches(rawPassword, hashedPassword);
       return rawPassword.equals(hashedPassword);
    }
}

