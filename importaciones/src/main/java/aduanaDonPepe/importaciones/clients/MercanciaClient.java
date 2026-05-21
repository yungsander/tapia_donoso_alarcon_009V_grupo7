package aduanaDonPepe.importaciones.clients;

import aduanaDonPepe.importaciones.config.FeignConfig;
import aduanaDonPepe.importaciones.dtos.response.MercanciaResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}