package alertas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/dummy_test_db",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    "spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect",
    "spring.jpa.hibernate.ddl-auto=none"
})
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "mercancia.service.url=http://localhost:8080"
})
class AlertasApplicationTests {

	@Test
	void contextLoads() {
	}

}
