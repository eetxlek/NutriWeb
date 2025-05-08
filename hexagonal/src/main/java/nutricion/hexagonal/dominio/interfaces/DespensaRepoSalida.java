package nutricion.hexagonal.dominio.interfaces;

import java.util.Optional;
import nutricion.hexagonal.dominio.clases.Despensa;


public interface DespensaRepoSalida {
    void guardar(Despensa d);
    Optional<Despensa> findByUsuarioCorreoElectronicoAndProductoId(String correoElectronico, Integer id);
    boolean existe(String email, Integer id);
}

