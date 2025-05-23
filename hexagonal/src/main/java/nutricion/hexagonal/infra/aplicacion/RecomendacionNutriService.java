package nutricion.hexagonal.infra.aplicacion;

import java.util.Optional;

import org.springframework.stereotype.Service;
import nutricion.hexagonal.dominio.clases.Actividad;
import nutricion.hexagonal.dominio.clases.RecomendacionNutricional;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.DeClases.RecomendacionNutricionalRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.ActividadEntity;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;
import nutricion.hexagonal.infra.persistencia.repos.ActividadRepository;

@Service
public class RecomendacionNutriService {

    private final RecomendacionNutricionalRepoSalida recomendNutricionalRepo;

    private final ActividadRepository actividadRepo;

    public RecomendacionNutriService(RecomendacionNutricionalRepoSalida recomendNutricionalRepo,
            ActividadRepository actividadRepo) {
        this.recomendNutricionalRepo = recomendNutricionalRepo;
        this.actividadRepo = actividadRepo;
    }

    public RecomendacionNutricional obtenerPorUsuario(int idUsuario) {
        // Obtenemos la entidad
        RecomendacionNutricional entity = recomendNutricionalRepo.buscarPorIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Nivel de actividad no válido"));

        return entity;
    }

    public void calcular(Usuario usuario) {

        // Obtener factor
        ActividadEntity actividadEntity = actividadRepo.findByNivelActividad(usuario.getNivelActividad())
                .orElseThrow(() -> new RuntimeException("Nivel de actividad no válido"));

        Actividad actividad = toDomain(actividadEntity);

        float factorActividad = actividad.getFactorActividad();

        // Calcular TMB según sexo
        float tmb;
        if (usuario.getSexo().equalsIgnoreCase("Femenino")) {
            tmb = 447.593f + (9.247f * usuario.getPeso()) + (3.098f * usuario.getAltura())
                    - (4.330f * usuario.getEdad());
        } else {
            tmb = 88.362f + (13.397f * usuario.getPeso()) + (4.799f * usuario.getAltura())
                    - (5.677f * usuario.getEdad());
        }

        float caloriasRecomendadas = tmb * factorActividad;

        // Calorías de macronutrientes
        float caloriasProteinas = caloriasRecomendadas * 0.3f;
        float caloriasGrasas = caloriasRecomendadas * 0.25f;
        float caloriasCarbohidratos = caloriasRecomendadas * 0.45f;

        Optional<RecomendacionNutricional> existente = recomendNutricionalRepo.buscarPorIdUsuario(usuario.getId());
        RecomendacionNutricional rec;
        if (existente.isPresent()) {
            // ACTUALIZAR
            rec = existente.get();
        } else {
            // CREAR NUEVA
            rec = new RecomendacionNutricional();
            rec.setUsuario(usuario); // solo se setea al crear
        }

        rec.setUsuario(usuario);
        rec.setActividad(actividad);
        rec.setCaloriasRecomendadas(caloriasRecomendadas);
        rec.setPorcentajeProteinas(caloriasProteinas);
        rec.setPorcentajeGrasas(caloriasGrasas);
        rec.setPorcentajeCarbohidratos(caloriasCarbohidratos);

        // Guarda en la base de datos
        recomendNutricionalRepo.guardar(rec);

    }

    // Método para convertir ActividadEntity a Actividad
    private Actividad toDomain(ActividadEntity actividadEntity) {
        return new Actividad(
                actividadEntity.getId(),
                actividadEntity.getNivelActividad(),
                actividadEntity.getFactorActividad());
    }

    public UsuarioEntity toEntity(Usuario usuario) { // se espera que ya venga encriptada
        // encripta al crear UsuarioEntity

        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(), 
                usuario.getCorreoElectronico(),
                usuario.getContraseña(), 
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
                entity.getContraseña(), // Asumimos que ya viene encriptada
                entity.getEdad(),
                entity.getSexo(),
                entity.getPeso(),
                entity.getAltura(),
                entity.getNivelActividad(),
                entity.getMetaSalud(),
                entity.getTipoDieta(),
                entity.getTipoUsuario());
    }

}
