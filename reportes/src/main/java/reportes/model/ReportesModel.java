package reportes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reportes")
@Data
public class ReportesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    private Integer anioTramite;
    private Integer mesTramite;
    private String nombreAgencia;
    private String numeroManifiesto;
    private Double totalImpuestosPagados;

}
