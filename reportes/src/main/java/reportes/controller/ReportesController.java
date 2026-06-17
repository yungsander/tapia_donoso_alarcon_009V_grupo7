package reportes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reportes.dto.request.FiltroReporteAnualDTO;
import reportes.dto.request.FiltroReporteMensualDTO;
import reportes.dto.response.ReporteAduaneroResponseDTO;
import reportes.service.ReportesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "reportes", description = "generarcion de reportes aduaneros")
@RestController
@RequestMapping("/api/reportes")
public class ReportesController {

    private final ReportesService reportesService;

    ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }
    
    @Operation(summary = "generar reporte mensual",
        description = "genera un reporte detallado filtrando por año y mes")
    @PostMapping("/mensual")
    public ResponseEntity<ReporteAduaneroResponseDTO> generarMensual(
        @RequestBody FiltroReporteMensualDTO filtro) {
            ReporteAduaneroResponseDTO reporte = reportesService.generarMensual(filtro);
            return ResponseEntity.ok(reporte);       
        }
    
    @Operation(summary = "generar reporte anual",
        description = "genera un reporte detallado filtrando unicamente por año")
    @PostMapping("/anual")
    public ResponseEntity<ReporteAduaneroResponseDTO> generarAnual(
            @RequestBody FiltroReporteAnualDTO filtros) {
        ReporteAduaneroResponseDTO reporte = reportesService.generarAnual(filtros);
        return ResponseEntity.ok(reporte);
    
    }
    
    
}
