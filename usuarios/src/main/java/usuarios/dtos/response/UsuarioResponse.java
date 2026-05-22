package usuarios.dtos.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String estado;
}
