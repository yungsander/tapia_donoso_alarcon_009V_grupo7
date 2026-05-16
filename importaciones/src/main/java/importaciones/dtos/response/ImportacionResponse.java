package mercancias.importaciones.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ImportacionResponse {

    private Long id;
    private LocalDate fechaImportacion;
    private String paisOrigen;
    private String estado;
    private Long idMercancia;
    private Double peso;
    private String tipoMercancia;

    // Datos traídos desde Feign
    private MercanciaResponse mercancia;
}