package exportaciones.dtos.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExportacionResponse {

    private Long id;
    private LocalDate fechaExportacion;
    private String paisDestino;
    private String estado;
    private Long idMercancia;
    private Double peso;
    private String tipoMercancia;
    private MercanciaResponse mercancia;
}