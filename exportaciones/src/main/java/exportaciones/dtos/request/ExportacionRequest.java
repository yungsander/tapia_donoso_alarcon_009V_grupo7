package aduanaDonPepe.exportaciones.dtos.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExportacionRequest {

    @NotNull(message = "La fecha de exportación es obligatoria")
    private LocalDate fechaExportacion;

    @NotBlank(message = "El país destino es obligatorio")
    private String paisDestino;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El id de mercancía es obligatorio")
    private Long idMercancia;

    @NotNull(message = "El peso es obligatorio")
    private Double peso;

    @NotBlank(message = "El tipo de mercancía es obligatorio")
    private String tipoMercancia;
}