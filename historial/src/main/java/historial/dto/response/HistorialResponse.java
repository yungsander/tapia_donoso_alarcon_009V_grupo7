package historial.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class HistorialResponse {

    private Long idHistorial;
    private Long idUsuario;
    private LocalDate fechaIngreso;
    private LocalTime horaIngreso;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    private String puntoAcceso;
    private String tipoAcceso;
    private String estadoIngreso;

    
}
