package nutricion.hexagonal.infra.persistencia.repos.imple;

import org.springframework.stereotype.Repository;
import nutricion.hexagonal.dominio.clases.Consumo;
import nutricion.hexagonal.dominio.clases.Despensa;
import nutricion.hexagonal.dominio.clases.Producto;
import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.DeClases.UsuarioRepoSalida;
import nutricion.hexagonal.infra.persistencia.entidades.ConsumoEntity;
import nutricion.hexagonal.infra.persistencia.entidades.DespensaEntity;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;
import nutricion.hexagonal.infra.persistencia.entidades.UsuarioEntity;
import nutricion.hexagonal.infra.persistencia.repos.UsuarioRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepoImple implements UsuarioRepoSalida {

    private final UsuarioRepository jpaRepo;

    public UsuarioRepoImple(UsuarioRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return jpaRepo.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepo.findByCorreoElectronico(email)
                .map(this::toDomain);
    }

    @Override
    public Set<Consumo> obtenerConsumos(String correo) {
        UsuarioEntity usuario = jpaRepo.findWithConsumosByCorreoElectronico(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toDomainConsumo(usuario.getConsumos()); // consumoEntity set to setConsumo
    }

    @Override
    public Set<Despensa> obtenerDespensa(String correo) {
        UsuarioEntity usuario = jpaRepo.findWithDespensaByCorreoElectronico(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getDespensa().stream()
                .map(this::toDomainDespensa)
                .collect(Collectors.toSet());
    }

    @Override
    public void guardar(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario); // convertimos dominio MODELO a ENTIDAD JPA
        jpaRepo.save(entity); // lo GUARDA con Spring Data (persistencia)
    }

    @Override
    public boolean existeEmail(String email) {
        return jpaRepo.existsByCorreoElectronico(email);
    }

    // Métodos auxiliares de mapeo
    private Set<Consumo> toDomainConsumo(Set<ConsumoEntity> entidades) {
        return entidades.stream()
                .map(this::toDomainConsumo) // usa el método individual
                .collect(Collectors.toSet());
    }

    private Despensa toDomainDespensa(DespensaEntity entity) {
        Producto producto = toDomainProducto(entity.getProducto());
        Integer idUsuario = entity.getIdUsuario().getId();
        return new Despensa(entity.getId(), idUsuario, producto, entity.getCantidad(), entity.getFechaCompra());
    }

    private Consumo toDomainConsumo(ConsumoEntity entity) {
        Producto producto = toDomainProducto(entity.getProducto());
        Integer idUsuario = entity.getIdUsuario().getId(); // Asegúrate que getUsuario() existe en la entidad
        return new Consumo(producto, idUsuario, entity.getCantidad(), entity.getFechaConsumo());
    }

    private Producto toDomainProducto(ProductoEntity entity) {
        return new Producto(entity.getId(), entity.getNombreProducto());
    }

    // convierte userEntity infra a usuario dominio, perfecto
    private Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getCorreoElectronico(),
                entity.getContraseña(),
                entity.getEdad(), 
                entity.getSexo(),
                entity.getPeso(),
                entity.getAltura(),
                entity.getNivelActividad(),
                entity.getMetaSalud(),
                entity.getTipoDieta(),
                entity.getTipoUsuario())
        ;
    }

    private UsuarioEntity toEntity(Usuario usuario) {
        // encripta al crear UsuarioEntity

        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(), 
                usuario.getCorreoElectronico(),
                usuario.getContraseña(), // viene encriptada por el servicio // separa persistencia de logica encript
                usuario.getEdad(), 
                usuario.getSexo(),
                usuario.getPeso(),
                usuario.getAltura(),
                usuario.getNivelActividad(),
                usuario.getMetaSalud(),
                usuario.getTipoDieta(),
                usuario.getTipoUsuario());
    }

}
