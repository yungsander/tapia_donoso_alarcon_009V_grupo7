package reportes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reportes.dto.request.FiltroReporteDTO;
import reportes.dto.response.ReporteItemDTO;
import reportes.service.ReportesService;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {
    private final ReportesService service;

    public ReportesController(ReportesService service) {
        this.service = service;
    }

    @PostMapping("/generar")
    public ResponseEntity<List<ReporteItemDTO>> generar(@RequestBody FiltroReporteDTO filtro) {
        return ResponseEntity.ok(service.generarReporte(filtro));
    }

}
