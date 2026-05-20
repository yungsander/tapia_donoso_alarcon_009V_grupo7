package historial.dto.response;

import lombok.Data;

@Data
public class UsuarioResponse {

    private Long idUsuario;
    private String nombre;
    private String rut;
    private String cargo;
    
}
