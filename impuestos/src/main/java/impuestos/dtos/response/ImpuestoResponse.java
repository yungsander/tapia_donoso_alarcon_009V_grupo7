package impuestos.dtos.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImpuestoResponse {

    private Long id;
    private String tipoImpuesto;
    private Double tasaImpuesto;

    // Monto calculado: tasaImpuesto% sobre el valor de la mercancía
    private Double montoCalculado;

    private LocalDate fechaAplicacion;
    private String estado;
    private Long idMercancia;
    private MercanciaResponse mercancia;
}
