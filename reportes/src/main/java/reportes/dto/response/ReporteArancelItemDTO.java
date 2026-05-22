package reportes.dto.response;

import lombok.Data;

@Data
public class ReporteArancelItemDTO {

    private Long numeroManifiesto;
    private Integer agencia;
    private String totalPagado;
    
}
