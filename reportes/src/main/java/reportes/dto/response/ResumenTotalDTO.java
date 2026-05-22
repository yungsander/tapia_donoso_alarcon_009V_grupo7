package reportes.dto.response;

import lombok.Data;

@Data
public class ResumenTotalDTO {

    private Integer totalOperaciones;
    private Integer totalImpuestosRecaudados;
    private Integer totalBultosIngresados;
    private Integer tramitesLiquidados;
    private Integer tramitesRetenidos;
    
}
