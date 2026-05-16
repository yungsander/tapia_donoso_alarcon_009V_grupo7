package mercancias.importaciones.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "importaciones")
public class ImportacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha de la importación
    @Column(name = "fecha_importacion", nullable = false)
    private LocalDate fechaImportacion;

    // País de origen
    @Column(name = "pais_origen", nullable = false)
    private String paisOrigen;

    // Estado de la importación
    @Column(nullable = false)
    private String estado;

    // ID de la mercancía
    @Column(name = "id_mercancia", nullable = false)
    private Long idMercancia;

    // Peso de la mercancía
    @Column(nullable = false)
    private Double peso;

    // Tipo de mercancía
    @Column(name = "tipo_mercancia", nullable = false)
    private String tipoMercancia;
}