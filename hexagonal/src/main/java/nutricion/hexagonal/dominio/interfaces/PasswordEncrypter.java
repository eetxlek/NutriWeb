package nutricion.hexagonal.dominio.interfaces;

//buena practica como puerto, define intencion
public interface PasswordEncrypter {
    String encode(String rawPassword); 
    boolean matches(String rawPassword, String hashedPassword);
}

