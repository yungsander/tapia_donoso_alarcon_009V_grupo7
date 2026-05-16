package mercancias.importaciones.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ImportacionRequest {

    @NotNull(message = "La fecha de importación es obligatoria")
    private LocalDate fechaImportacion;

    @NotBlank(message = "El país de origen es obligatorio")
    private String paisOrigen;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El id de mercancía es obligatorio")
    private Long idMercancia;

    @NotNull(message = "El peso es obligatorio")
    private Double peso;

    @NotBlank(message = "El tipo de mercancía es obligatorio")
    private String tipoMercancia;
}