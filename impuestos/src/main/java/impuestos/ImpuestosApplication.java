package impuestos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ImpuestosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImpuestosApplication.class, args);
	}

}
