package nutricion.hexagonal.dominio.interfaces.DeClases;

import java.util.Optional;
import java.util.Set;
import nutricion.hexagonal.dominio.clases.Despensa;


public interface DespensaRepoSalida {
   
    Optional<Despensa> findByUsuarioCorreoElectronicoAndProductoId(String correoElectronico, Integer id);
    Optional<Despensa> findById(Integer id);
    boolean existe(String correoElectronico, Integer id);
    Set<Despensa> findByUsuarioCorreoElectronico(String correoElectronico);
    void guardar(Despensa despensa); 
}

