package nutricion.hexagonal.dominio.interfaces;

import java.util.Optional;
import nutricion.hexagonal.dominio.clases.Despensa;


public interface DespensaRepoSalida {
    void guardar(Despensa d);
    Optional<Despensa> findDespensaByUsuarioIdAndProductoId(int idUsuario, Integer id);
    boolean existe(int idUsuario, Integer id);
}

