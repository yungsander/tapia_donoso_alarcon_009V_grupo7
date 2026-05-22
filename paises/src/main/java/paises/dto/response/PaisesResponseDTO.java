package paises.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisesResponseDTO {
    
    private Long id;
    private String codigoIso;
    private String nombre;
}
