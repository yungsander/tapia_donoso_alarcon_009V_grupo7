package dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlertRequest {

    @NotBlank(message = "El nombre de la alerta es obligatorio")
    private String nombreAlerta;

    @NotBlank(message = "El mensaje de la alerta es obligatorio")
    private String mensajeAlerta;

    @NotNull(message = "el id de la mercancia es obligatoria")
    private Long idMercancia;
}
