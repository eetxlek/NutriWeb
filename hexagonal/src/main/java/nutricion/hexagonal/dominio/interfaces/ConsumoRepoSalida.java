package nutricion.hexagonal.dominio.interfaces;

import nutricion.hexagonal.dominio.clases.Consumo;

public interface ConsumoRepoSalida {
    void guardar(Consumo c);
}
