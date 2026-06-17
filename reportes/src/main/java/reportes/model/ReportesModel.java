package reportes.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reportes")
public class ReportesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;
    
    @Column(name = "numero_manifiesto", nullable = false)
    private String numeroManifiesto;

    @Column(name = "fecha_y_hora_ingreso")
    private LocalDateTime fechaHoraIngreso;

    @Column(name = "año_tramite")
    private Integer anioTramite;

    @Column(name = "mes_tramite")
    private Integer mesTramite;

    @Column(name = "nombre_agencia")
    private String nombreAgencia;

    @Column(name = "categoria_mercancia")
    private Integer categoriaMercancia;

    @Column(name = "total_impuestos_pagados")
    private Double totalImpuestosPagados;

    @Column(name = "estado_final")
    private Integer estadoFinal;

}
