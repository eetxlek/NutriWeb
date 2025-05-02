package nutricion.hexagonal.infra.persistencia.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nutricion.hexagonal.infra.persistencia.entidades.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
    Optional<ProductoEntity> findByNombreProducto(String nombreProducto);
}

