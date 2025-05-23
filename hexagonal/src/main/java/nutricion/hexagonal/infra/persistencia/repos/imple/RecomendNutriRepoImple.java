package nutricion.hexagonal.infra.persistencia.repos.imple;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import nutricion.hexagonal.dominio.clases.Actividad;
import nutricion.hexagonal.dominio.clases.RecomendacionNutricional;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.DeClases.RecomendacionNutricionalRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.ActividadEntity;
import nutricion.hexagonal.infra.persistencia.entidades.RecomendacionNutricionalEntity;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;
import nutricion.hexagonal.infra.persistencia.repos.ActividadRepository;
import nutricion.hexagonal.infra.persistencia.repos.RecomendNutriRepository;

@Repository
public class RecomendNutriRepoImple implements RecomendacionNutricionalRepoSalida {

    private final RecomendNutriRepository repo;
    private final ActividadRepository actividadEntityRepository;

    public RecomendNutriRepoImple(RecomendNutriRepository repo, ActividadRepository actividadEntityRepository) {
        this.repo = repo;
        this.actividadEntityRepository = actividadEntityRepository;
    }

    @Override
    public void guardar(RecomendacionNutricional recomendacion) {
        RecomendacionNutricionalEntity entity = toEntity(recomendacion);
        repo.save(entity); // requiere entity
    }

    @Override
    public Optional<RecomendacionNutricional> buscarPorIdUsuario(int idUsuario) {
        return repo.findByUsuario_Id(idUsuario)
                .map(this::toDomain);
    }

    private RecomendacionNutricionalEntity toEntity(RecomendacionNutricional recomendacion) {

        RecomendacionNutricionalEntity entity = new RecomendacionNutricionalEntity();
        entity.setId(recomendacion.getId());
        entity.setUsuario(toEntity(recomendacion.getUsuario())); // solo si ya es un UsuarioEntity gestionado
        entity.setActividad(toEntity(recomendacion.getActividad())); // obtener desde repositorio
        entity.setCaloriasRecomendadas(recomendacion.getCaloriasRecomendadas());
        entity.setPorcentajeProteinas(recomendacion.getPorcentajeProteinas());
        entity.setPorcentajeGrasas(recomendacion.getPorcentajeGrasas());
        entity.setPorcentajeCarbohidratos(recomendacion.getPorcentajeCarbohidratos());

        return entity;
    }

    private ActividadEntity toEntity(Actividad actividad) {
        return actividadEntityRepository.findById(actividad.getId())
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con id: " + actividad.getId()));
    }

    public UsuarioEntity toEntity(Usuario usuario) {
        // encripta al crear UsuarioEntity

        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getContraseña(), // Aquí ya viene la contraseña encriptada por el servicio // separa
                                         // persistencia de logica cript
                usuario.getEdad(),
                usuario.getSexo(),
                usuario.getPeso(),
                usuario.getAltura(),
                usuario.getNivelActividad(),
                usuario.getMetaSalud(),
                usuario.getTipoDieta(),
                usuario.getTipoUsuario());
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getCorreoElectronico(),
                entity.getContraseña(), // viene encriptada
                entity.getEdad(),
                entity.getSexo(),
                entity.getPeso(),
                entity.getAltura(),
                entity.getNivelActividad(),
                entity.getMetaSalud(),
                entity.getTipoDieta(),
                entity.getTipoUsuario());
    }

    private RecomendacionNutricional toDomain(RecomendacionNutricionalEntity entity) {
        return new RecomendacionNutricional(
                entity.getId(),
                toDomain(entity.getUsuario()),
                toDomain(entity.getActividad()),
                entity.getCaloriasRecomendadas(),
                entity.getPorcentajeProteinas(),
                entity.getPorcentajeGrasas(),
                entity.getPorcentajeCarbohidratos());
    }

    private Actividad toDomain(ActividadEntity actividadEntity) {
        return new Actividad(
                actividadEntity.getId(),
                actividadEntity.getNivelActividad(),
                actividadEntity.getFactorActividad());
    }

}
