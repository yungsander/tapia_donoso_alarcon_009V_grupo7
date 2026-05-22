package reportes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import reportes.dto.request.FiltroReporteAnualDTO;
import reportes.dto.request.FiltroReporteMensualDTO;
import reportes.dto.response.ReporteAduaneroResponseDTO;
import reportes.dto.response.ReporteArancelItemDTO;
import reportes.model.ReportesModel;
import reportes.repository.ReportesRepository;

@Service
public class ReportesService {

    private final ReportesRepository reportesRepository;

    public ReportesService(ReportesRepository reportesRepository) {
        this.reportesRepository = reportesRepository;
    }
    
    public ReporteAduaneroResponseDTO generarMensual(FiltroReporteMensualDTO filtro) {
        List<ReportesModel> datosBd = reportesRepository
                .findByAnioTramiteAndMesTramite(filtro.getAnio(), filtro.getMes());
        
        ReporteAduaneroResponseDTO respuesta = new ReporteAduaneroResponseDTO();
        respuesta.setTituloReporte("reporte aduanero - mes: " + filtro.getMes() + " / " + filtro.getAnio());

        List<ReporteArancelItemDTO> detalles = convertirModelADto(datosBd);
        respuesta.setDetalleOperaciones(detalles);
        
        return respuesta;

    }

    public ReporteAduaneroResponseDTO generarAnual(FiltroReporteAnualDTO filtro) {
        
        List<ReportesModel> datosBd = reportesRepository.findByAnioTramite(filtro.getAnio());

        ReporteAduaneroResponseDTO respuesta = new ReporteAduaneroResponseDTO();
        respuesta.setTituloReporte("reporte aduanero anual - " + filtro.getAnio());

        List<ReporteArancelItemDTO> detalles = convertirModelADto(datosBd);
        respuesta.setDetalleOperaciones(detalles);

        return respuesta;
    }

    private List<ReporteArancelItemDTO> convertirModelADto(List<ReportesModel> datosBd) {
        List<ReporteArancelItemDTO> listaDetalles = new ArrayList<>();

        for (ReportesModel modelo : datosBd) {
            ReporteArancelItemDTO itemDto = new ReporteArancelItemDTO();
            itemDto.setNumeroManifiesto(modelo.getNumeroManifiesto());
            itemDto.setAgencia(modelo.getNombreAgencia());
            itemDto.setTotalPagado("$" + modelo.getTotalImpuestosPagados());

            listaDetalles.add(itemDto);
        }

        return listaDetalles;
    }
}
