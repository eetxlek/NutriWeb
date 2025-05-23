package nutricion.hexagonal.infra.aplicacion;

import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import nutricion.hexagonal.dominio.clases.Actividad;
import nutricion.hexagonal.dominio.clases.Consumo;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.dominio.clases.RecomendacionNutricional;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.DeClases.UsuarioRepoSalida;
import nutricion.hexagonal.dominio.interfaces.DeClases.ActividadRepoSalida;
import nutricion.hexagonal.dominio.interfaces.DeClases.RecomendacionNutricionalRepoSalida;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.RegistroRequestDTO;
import nutricion.hexagonal.infra.adaptadores.salida.encrypter.BcryptPasswordEncrypter;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;

@Service
public class UsuarioService {

    private final UsuarioRepoSalida usuarioRepo;
    private final ActividadRepoSalida actividadRepo;
    private final RecomendacionNutricionalRepoSalida recomenRepo;
    private final BcryptPasswordEncrypter passwordEncrypter;

    public UsuarioService(
            UsuarioRepoSalida usuarioRepo,
            ActividadRepoSalida actividadRepo,
            RecomendacionNutricionalRepoSalida recRepo,
            BcryptPasswordEncrypter passwordEncrypter) {
        this.usuarioRepo = usuarioRepo;
        this.actividadRepo = actividadRepo;
        this.recomenRepo = recRepo;
        this.passwordEncrypter = passwordEncrypter;
    }

    @Transactional
    public void registrarUsuario(RegistroRequestDTO dto) {
        if (usuarioRepo.buscarPorEmail(dto.getCorreoElectronico()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }
        // encripta
        String encriptado = passwordEncrypter.encode(dto.getPassword());
        Usuario usuario = fromDto(dto, encriptado);
        usuarioRepo.guardar(usuario);
    }

    @Transactional
    public void registrarRecomendacion(RegistroRequestDTO dto) {
        // Obtén el usuario ya registrado
        Usuario usuario = usuarioRepo.buscarPorEmail(dto.getCorreoElectronico())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Calcular IMC
        float alturaMetros = (float) (dto.getAltura() / 100f);
        float imc = (float) dto.getPeso() / (alturaMetros * alturaMetros);

        // Obtener factor de actividad del repo
        Actividad actividad = actividadRepo.findByNivelActividad(dto.getNivelActividad())
                .orElseThrow(() -> new RuntimeException("Nivel de actividad no válido"));
        float factorActividad = actividad.getFactorActividad();

        // FORMULA recomendacion SEGUN sexo
        float tmb;
        if (dto.getSexo().equals("Femenino")) {
            tmb = 447.593f + (9.247f * (float) dto.getPeso()) + (3.098f * (float) dto.getAltura())
                    - (4.330f * dto.getEdad());
        } else {
            tmb = 88.362f + (13.397f * (float) dto.getPeso()) + (4.799f * (float) dto.getAltura())
                    - (5.677f * dto.getEdad());
        }
        // Recomendacion
        float caloriasRecomendadas = tmb * factorActividad;
        // Calcular las cantidades de cada macronutriente según los porcentajes
        float caloriasProteinas = caloriasRecomendadas * 0.3f; // 30% de las calorías serán proteínas
        float caloriasGrasas = caloriasRecomendadas * 0.25f; // 25% de las calorías serán grasas
        float caloriasCarbohidratos = caloriasRecomendadas * 0.45f; //

        // Crear entrada en Recomendacion_Nutricional
        RecomendacionNutricional rec = new RecomendacionNutricional();
        rec.setUsuario(usuario);
        rec.setActividad(actividad);
        rec.setCaloriasRecomendadas(caloriasRecomendadas);
        rec.setPorcentajeProteinas(caloriasProteinas); // 0.3f
        rec.setPorcentajeGrasas(caloriasGrasas); // 0.25f
        rec.setPorcentajeCarbohidratos(caloriasCarbohidratos); // 0.45f

        recomenRepo.guardar(rec);
    }

    @Transactional
    public void registrar(RegistroRequestDTO dto) {
        if (usuarioRepo.buscarPorEmail(dto.getCorreoElectronico()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }
        // encripta
        String encriptado = passwordEncrypter.encode(dto.getPassword());
        // pasa encriptado
        Usuario usuario = fromDto(dto, encriptado);

        // Calcular IMC
        float alturaMetros = (float) (dto.getAltura() / 100f);
        float imc = (float) dto.getPeso() / (alturaMetros * alturaMetros);

        // Obtener factor de actividad del repo
        Actividad actividad = actividadRepo.findByNivelActividad(dto.getNivelActividad())
                .orElseThrow(() -> new RuntimeException("Nivel de actividad no válido"));
        float factorActividad = actividad.getFactorActividad();

        // FORMULA recomendacion SEGUN sexo
        float tmb;
        if (dto.getSexo().equals("Femenino")) {
            tmb = 447.593f + (9.247f * (float) dto.getPeso()) + (3.098f * (float) dto.getAltura())
                    - (4.330f * dto.getEdad());
        } else {
            tmb = 88.362f + (13.397f * (float) dto.getPeso()) + (4.799f * (float) dto.getAltura())
                    - (5.677f * dto.getEdad());
        }
        // Recomendacion
        float caloriasRecomendadas = tmb * factorActividad;

        usuarioRepo.guardar(usuario);

        // Calcular las cantidades de cada macronutriente según los porcentajes
        float caloriasProteinas = caloriasRecomendadas * 0.3f; // 30% de las calorías serán proteínas
        float caloriasGrasas = caloriasRecomendadas * 0.25f; // 25% de las calorías serán grasas
        float caloriasCarbohidratos = caloriasRecomendadas * 0.45f; //

        // Crear entrada en Recomendacion_Nutricional
        RecomendacionNutricional rec = new RecomendacionNutricional();
        rec.setId(usuario.getId());
        rec.setActividad(actividad);
        rec.setCaloriasRecomendadas(caloriasRecomendadas);
        rec.setPorcentajeProteinas(caloriasProteinas); // 0.3f
        rec.setPorcentajeGrasas(caloriasGrasas); // 0.25f
        rec.setPorcentajeCarbohidratos(caloriasCarbohidratos); // 0.45f

        recomenRepo.guardar(rec);
    }

    public Usuario actualizar(Usuario dto) {
        Optional<Usuario> usuarioExistente = usuarioRepo.buscarPorEmail(dto.getCorreoElectronico());
        if (!usuarioExistente.isPresent()) {
            throw new RuntimeException("El usuario no existe");
        }
        usuarioRepo.guardar(dto);
        return dto;
    }

    public boolean verificarLogin(String email, String rawPassword) {
        return usuarioRepo.buscarPorEmail(email)
                .map(user -> passwordEncrypter.matches(rawPassword, user.getContraseña()))
                .orElse(false);
    }

    // Método adaptado para buscar usuario por email
    public boolean existeEmail(String email) {
        return usuarioRepo.existeEmail(email);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        // Buscar el usuario por correo electrónico
        return usuarioRepo.buscarPorEmail(email);
    }

    public Set<Consumo> obtenerConsumos(String correo) {
        return usuarioRepo.obtenerConsumos(correo);
    }

    public Set<Despensa> obtenerDespensa(String correo) {
        return usuarioRepo.obtenerDespensa(correo);
    }

    public Usuario fromEntity(UsuarioEntity entity) {
        Usuario usuario = new Usuario();
        usuario.setId(entity.getId());
        usuario.setNombre(entity.getNombre());
        usuario.setCorreoElectronico(entity.getCorreoElectronico());
        usuario.setContraseña(entity.getContraseña()); // Faltaba
        usuario.setEdad(entity.getEdad());
        usuario.setPeso(entity.getPeso());
        usuario.setAltura(entity.getAltura());
        usuario.setNivelActividad(entity.getNivelActividad());
        usuario.setMetaSalud(entity.getMetaSalud());
        usuario.setTipoDieta(entity.getTipoDieta());
        usuario.setTipoUsuario(entity.getTipoUsuario());
        return usuario;
    }

    public Usuario fromDto(RegistroRequestDTO dto, String encriptada) {

        return new Usuario(
                0,
                dto.getNombre(),
                dto.getCorreoElectronico(),
                encriptada,
                dto.getEdad(),
                dto.getSexo(),
                (float) dto.getPeso(),
                (float) dto.getAltura(),
                dto.getNivelActividad(),
                dto.getMetaSalud(),
                dto.getTipoDieta(),
                dto.getTipoUsuario());
    }

    UsuarioEntity toEntity(Usuario usuario) { // se espera que ya venga encriptada
        // encripta al crear UsuarioEntity

        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(), // Suponiendo que la clase Usuario tiene el campo nombre
                usuario.getCorreoElectronico(),
                usuario.getContraseña(), // Aquí ya viene la contraseña encriptada por el servicio // separa
                                         // persistencia de logica cript
                usuario.getEdad(), // Suponiendo que la clase Usuario tiene estos campos
                usuario.getSexo(),
                usuario.getPeso(),
                usuario.getAltura(),
                usuario.getNivelActividad(),
                usuario.getMetaSalud(),
                usuario.getTipoDieta(),
                usuario.getTipoUsuario());
    }
}