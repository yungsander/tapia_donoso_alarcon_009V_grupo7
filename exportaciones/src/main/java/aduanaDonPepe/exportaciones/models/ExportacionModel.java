package aduanaDonPepe.exportaciones.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exportaciones")
public class ExportacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha de la exportación
    @Column(name = "fecha_exportacion", nullable = false)
    private LocalDate fechaExportacion;

    // País destino
    @Column(name = "pais_destino", nullable = false, length = 100)
    private String paisDestino;

    // Estado de la exportación
    @Column(nullable = false, length = 50)
    private String estado;

    // ID de la mercancía
    @Column(name = "id_mercancia", nullable = false)
    private Long idMercancia;

    // Peso de la mercancía
    @Column(nullable = false)
    private Double peso;

    // Tipo de mercancía
    @Column(name = "tipo_mercancia", nullable = false, length = 100)
    private String tipoMercancia;

}
