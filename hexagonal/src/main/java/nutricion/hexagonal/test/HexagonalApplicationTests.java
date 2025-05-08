package nutricion.hexagonal.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootTest
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class HexagonalApplicationTests {
    @Test
    void contextLoads() {
    }
}
