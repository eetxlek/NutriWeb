package nutricion.hexagonal.test;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import nutricion.hexagonal.infra.persistencia.entidades.DespensaEntity;

@SpringBootTest
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class HexagonalApplicationTests {
    @Test
    void contextLoads() {
    }

    // @Test
    // void testBusquedaDespensaPorEmailYProducto() {
    //     Optional<DespensaEntity> resultado = despensaJpaRepo
    //             .findByUsuarioCorreoElectronicoAndProductoId("usuario@ejemplo.com", 22);
    //     assertTrue(resultado.isPresent());
    // }

}
