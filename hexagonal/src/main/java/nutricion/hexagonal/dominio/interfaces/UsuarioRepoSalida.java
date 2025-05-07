package nutricion.hexagonal.dominio.interfaces;

import java.util.Optional;

import nutricion.hexagonal.dominio.clases.Usuario;

//puerto SALIDA : define cómo acceder a una fuente de datos (como una base de datos).
//Es implementada por un adaptador (ej. JPA, // es implementado por un caso de uso o servicio. // la implementacion/servicio no conoce el dominio.
public interface UsuarioRepoSalida {

    Optional<Usuario> buscarPorEmail(String email);
    boolean existeEmail(String email);
    void guardar(Usuario usuario);
}