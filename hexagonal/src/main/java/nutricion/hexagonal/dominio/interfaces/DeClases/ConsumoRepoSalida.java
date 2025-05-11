package nutricion.hexagonal.dominio.interfaces.DeClases;

import nutricion.hexagonal.dominio.clases.Consumo;

public interface ConsumoRepoSalida {
    void guardar(Consumo c);
}
