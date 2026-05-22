package dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertResponse {

    private Long id;
    private String nombreAlerta;
    private String mensajeAlerta;
    private MercanciaResponse mercancia;
}
