package impuestos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    // 1. Soluciona el error del cliente externo (Feign)
    "mercancia.service.url=http://localhost:8080",
    
    // 2. Soluciona el error de Hibernate/JPA dándole datos falsos pero válidos
    "spring.datasource.url=jdbc:mysql://localhost:3306/dummy_test_db",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    "spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect",
    
    // 3. Evita que intente crear tablas en esa base de datos falsa
    "spring.jpa.hibernate.ddl-auto=none"
})
class ImpuestosApplicationTests {
	@Test
	void contextLoads() {
	}

}
