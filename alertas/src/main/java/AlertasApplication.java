

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"clients", "services", "controllers", "repositories"})
public class AlertasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertasApplication.class, args);
	}

}
