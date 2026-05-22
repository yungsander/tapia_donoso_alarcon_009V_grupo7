package impuestos.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "impuesto")
public class ImpuestoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tipo de impuesto (ej: IVA, ARANCEL, TASA_ESPECIAL)
    @Column(name = "tipo_impuesto", nullable = false, length = 100)
    private String tipoImpuesto;

    // Porcentaje aplicado sobre el valor de la mercancía
    @Column(name = "tasa_impuesto", nullable = false)
    private Double tasaImpuesto;

    // Monto calculado automáticamente: tasa% del valor de la mercancía
    @Column(name = "monto_calculado", nullable = false)
    private Double montoCalculado;

    // Fecha en que se aplica el impuesto
    @Column(name = "fecha_aplicacion", nullable = false)
    private LocalDate fechaAplicacion;

    // Estado del cobro: PENDIENTE, PAGADO, EXENTO
    @Column(nullable = false, length = 50)
    private String estado;

    // ID de la mercancía sobre la cual se aplica el impuesto
    @Column(name = "id_mercancia", nullable = false)
    private Long idMercancia;

}
