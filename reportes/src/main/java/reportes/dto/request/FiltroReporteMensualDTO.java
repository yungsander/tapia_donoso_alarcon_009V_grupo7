package reportes.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FiltroReporteMensualDTO {

    @NotNull(message = "el año es obligatorio")
    private Integer anio;

    @NotNull(message = "el mes es obligatorio")
    private Integer mes;

    private String nombreAgencia;    
    
}
