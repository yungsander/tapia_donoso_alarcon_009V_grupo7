package reportes.service;

import org.springframework.stereotype.Service;
import reportes.dto.request.FiltroReporteDTO;
import reportes.dto.response.ReporteItemDTO;
import reportes.exception.ReporteNotFoundException;
import reportes.model.ReportesModel;
import reportes.repository.ReportesRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportesService {
    private final ReportesRepository repository;

    public ReportesService(ReportesRepository repository) {
        this.repository = repository;
    }

    public ReportesModel obtenerPorId(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ReporteNotFoundException("No existe el reporte con ID: " + id));
    }   

    public List<ReporteItemDTO> generarReporte(FiltroReporteDTO filtro) {
        return repository.findByAnioTramite(filtro.getAnio()).stream()
            .map(m -> ReporteItemDTO.builder()
                .agencia(m.getNombreAgencia())
                .totalPagado("$" + m.getTotalImpuestosPagados())
                .build())
            .collect(Collectors.toList());
    }
}
