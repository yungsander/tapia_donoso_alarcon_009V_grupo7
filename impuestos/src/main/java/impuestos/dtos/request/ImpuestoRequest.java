package impuestos.dtos.request;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ImpuestoRequest {

    @NotBlank(message = "El tipo de impuesto es obligatorio")
    private String tipoImpuesto;

    // Porcentaje aplicado sobre el valor de la mercancía (ej: 19.0 para 19%)
    @NotNull(message = "La tasa del impuesto es obligatoria")
    @DecimalMin(value = "0.0", inclusive = false, message = "La tasa debe ser mayor a 0")
    private Double tasaImpuesto;

    @NotNull(message = "La fecha de aplicación es obligatoria")
    private LocalDate fechaAplicacion;

    // Estado del impuesto: PENDIENTE, PAGADO, EXENTO
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El id de mercancía es obligatorio")
    private Long idMercancia;
}
