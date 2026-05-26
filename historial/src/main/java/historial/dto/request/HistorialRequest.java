package historial.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HistorialRequest {

    @NotNull(message = "el id del usuario es obligatorio")
    private Long idUsuario;

    @NotNull
    private LocalDate fechaIngreso;

    @NotNull 
    private LocalTime horaIngreso;

    @NotBlank(message = "el punto de acceso no puede estar vacio")
    private String puntoAcceso;

    @NotBlank
    private String tipoAcceso;

    @NotBlank
    private String estadoIngreso;

    @NotBlank(message = "no puede estar vacio")
    private boolean Vigente;
}
