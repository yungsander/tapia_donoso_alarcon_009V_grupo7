package clients;

import config.FeignConfig;
import dtos.response.MercanciaResponse;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// Cliente Feign para consumir el microservicio de mercancías
@FeignClient(
        name = "mercancia-client",
        url = "${mercancia.service.url}",
        configuration = FeignConfig.class
)
public interface MercanciaClient {

    // Llama al endpoint GET /api/v1/mercancias/{id}
    @GetMapping("/api/v1/mercancias/{id}")
    MercanciaResponse obtenerMercanciaPorId(@PathVariable("id") Long id);

    @GetMapping("/api/v1/mercancias/batch")
    List<MercanciaResponse> obtenerMercanciasPorIds(@RequestParam("ids") List<Long> ids);

}
