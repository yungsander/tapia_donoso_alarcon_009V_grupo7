package reportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reportes.dto.request.FiltroReporteAnualDTO;
import reportes.dto.request.FiltroReporteMensualDTO;
import reportes.dto.response.ReporteAduaneroResponseDTO;
import reportes.service.ReportesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/reportes")
public class ReportesController {

    @Autowired
    private ReportesService reportesService;
    
    @PostMapping("/mensual")
    public ResponseEntity<ReporteAduaneroResponseDTO> generarMensual(
        @RequestBody FiltroReporteMensualDTO filtro) {

            ReporteAduaneroResponseDTO reporte = reportesService.generarMensual(filtro);
            return ResponseEntity.ok(reporte);       
        }
    
    @PostMapping("/anual")
    public ResponseEntity<ReporteAduaneroResponseDTO> generarAnual(
            @RequestBody FiltroReporteAnualDTO filtros) {
        
        ReporteAduaneroResponseDTO reporte = reportesService.generarAnual(filtros);
        return ResponseEntity.ok(reporte);
    
    }
    
    
}
