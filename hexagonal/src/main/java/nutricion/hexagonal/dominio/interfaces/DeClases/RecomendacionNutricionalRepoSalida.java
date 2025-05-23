package nutricion.hexagonal.dominio.interfaces.DeClases;

import java.util.Optional;

import nutricion.hexagonal.dominio.clases.RecomendacionNutricional;

public interface RecomendacionNutricionalRepoSalida {
    void guardar(RecomendacionNutricional recomendacion);
      Optional<RecomendacionNutricional> buscarPorIdUsuario(int idUsuario);
}
