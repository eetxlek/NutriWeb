package nutricion.hexagonal.infra.seguridad.adaptadores.salida;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nutricion.hexagonal.infra.persistencia.entidades.ComposicionEntity;
import nutricion.hexagonal.infra.persistencia.entidades.ConsumoEntity;
import nutricion.hexagonal.infra.persistencia.entidades.DespensaEntity;
import nutricion.hexagonal.infra.persistencia.entidades.DespensaRepository;
import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;
import nutricion.hexagonal.infra.persistencia.repos.ComposicionProductoRepository;
import nutricion.hexagonal.infra.persistencia.repos.ConsumoRepository;
import nutricion.hexagonal.infra.persistencia.repos.ProductoRepository;
import nutricion.hexagonal.infra.seguridad.adaptadores.entrada.dto.ProductoDTO;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private ComposicionProductoRepository composicionRepo;

    @Autowired
    private DespensaRepository despensaRepo;

    @Autowired
    private ConsumoRepository consumoRepo;

    public void guardarEnDespensa(ProductoDTO dto, int idUsuario) {
        ProductoEntity producto = productoRepo.findByNombreProducto(dto.getNombre())
            .orElseGet(() -> {
                ProductoEntity nuevo = new ProductoEntity();
                nuevo.setNombreProducto(dto.getNombre());
                return productoRepo.save(nuevo);
            });

        if (composicionRepo.findAll().stream().noneMatch(c -> c.getProducto().getId_producto().equals(producto.getId_producto()))) {
            ComposicionEntity comp = new ComposicionEntity();
            comp.setProducto(producto);
            comp.setCalorias(null); // Puedes calcular si lo deseas
            comp.setProteinas(dto.getProteinas() != null ? dto.getProteinas().floatValue() : 0f);
            comp.setGrasas(dto.getGrasa() != null ? dto.getGrasa().floatValue() : 0f);
            comp.setCarbohidratos(dto.getCarbs() != null ? dto.getCarbs().floatValue() : 0f);
            composicionRepo.save(comp);
        }

        DespensaEntity d = new DespensaEntity();
        d.setProducto(producto);
        d.setIdUsuario(idUsuario); // puedes obtenerlo desde sesión o token
        d.setCantidad(dto.getCantidad());
        d.setFechaCompra(LocalDate.parse(dto.getFechaCompraConsumo()));
        despensaRepo.save(d);
    }

    public void guardarEnConsumo(ProductoDTO dto, int idUsuario) {
        ProductoEntity producto = productoRepo.findByNombreProducto(dto.getNombre())
            .orElseThrow(() -> new RuntimeException("El producto no existe")); // O créalo como arriba

        ConsumoEntity c = new ConsumoEntity();
        c.setProducto(producto);
        c.setIdUsuario(idUsuario);
        c.setCantidad(dto.getCantidad());
        c.setFechaConsumo(LocalDate.parse(dto.getFechaCompraConsumo())); // misma fecha por ahora
        consumoRepo.save(c);
    }
}
