package nutricion.hexagonal.dominio.interfaces.DeClases;

import java.util.Optional;
import java.util.Set;
import nutricion.hexagonal.dominio.clases.Consumo;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.dominio.clases.Usuario;

public interface UsuarioRepoSalida {
    Optional<Usuario> buscarPorId(Integer id);

    Optional<Usuario> buscarPorEmail(String email);

    boolean existeEmail(String email);

    void guardar(Usuario usuario);

    Set<Consumo> obtenerConsumos(String correo);

    Set<Despensa> obtenerDespensa(String correo);
}