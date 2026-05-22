package reportes.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ReporteAduaneroResponseDTO {

    private String tituloReporte;
    private List<ReporteArancelItemDTO> detalleOperaciones;

    private ResumenTotalDTO resumenFinal;
    
}
