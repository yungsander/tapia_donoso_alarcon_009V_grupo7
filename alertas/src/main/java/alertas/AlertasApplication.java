package alertas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
	"alertas", "controllers", "services", "repositories",
	"dtos", "exceptions", "models", "config", "clients"
})
@EnableFeignClients(basePackages = "clients")
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "models")
@EnableDiscoveryClient
public class AlertasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertasApplication.class, args);
	}

}
