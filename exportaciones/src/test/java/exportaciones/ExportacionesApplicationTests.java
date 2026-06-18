package exportaciones;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "mercancia.service.url=http://localhost:8080",
    "spring.datasource.url=jdbc:mysql://localhost:3306/dummy_test_db",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    "spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect",
    "spring.jpa.hibernate.ddl-auto=none"
})
class ExportacionesApplicationTests {

	@Test
	void contextLoads() {
	}

}
