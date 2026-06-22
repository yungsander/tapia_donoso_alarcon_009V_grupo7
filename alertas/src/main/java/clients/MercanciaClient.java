package clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import config.FeignConfig;
import dtos.response.MercanciaResponse;

@FeignClient(
        name = "mercancias",
        configuration = FeignConfig.class
)
public interface MercanciaClient {

    @GetMapping("/api/v1/mercancias/{id}")
    MercanciaResponse obtenerMercanciaPorId(@PathVariable("id") Long id);

    @GetMapping("/api/v1/mercancias/batch")
    List<MercanciaResponse> obtenerMercanciasPorIds(@RequestParam("ids") List<Long> ids);


}
