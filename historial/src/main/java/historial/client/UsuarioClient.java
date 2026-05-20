package historial.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import historial.dto.response.UsuarioResponse;

@FeignClient(
    name = "usuario-client",
    url = "${usuario.service.url}",
    configuration = FeignClient.class
)
public interface UsuarioClient {
    
    @GetMapping("/api/v1/usuario({id}")
    UsuarioResponse obtenerUsuarioPorId(@PathVariable("id") Long id);
}
