package reportes.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FiltroReporteAnualDTO {

    @NotNull(message = "el año es obligatorio para el reporte anual")
    private Integer anio;

    private String nombreAgencia;
    
}
