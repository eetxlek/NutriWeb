package nutricion.hexagonal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;

@EnableConfigurationProperties
@SpringBootApplication
public class HexagonalApplication {

	
    @Autowired
    private JdbcTemplate jdbcTemplate;


	public static void main(String[] args) {
		SpringApplication.run(HexagonalApplication.class, args);
	}
 @PostConstruct
    public void testConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Conexión a MySQL exitosa.");
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
