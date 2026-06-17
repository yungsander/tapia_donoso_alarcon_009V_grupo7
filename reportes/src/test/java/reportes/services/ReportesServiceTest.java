package reportes.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import reportes.dto.request.FiltroReporteAnualDTO;
import reportes.dto.request.FiltroReporteMensualDTO;
import reportes.dto.response.ReporteAduaneroResponseDTO;
import reportes.model.ReportesModel;
import reportes.repository.ReportesRepository;
import reportes.service.ReportesService;

@ExtendWith(MockitoExtension.class)
public class ReportesServiceTest {

    @Mock
    private ReportesRepository reportesRepository;

    @InjectMocks
    private ReportesService reportesService;
    private ReportesModel modelo;

    @BeforeEach
    void setUp() {
        modelo = new ReportesModel();
        modelo.setNumeroManifiesto("MAN-123");
        modelo.setNombreAgencia("agencia norte");
        modelo.setTotalImpuestosPagados(1500.0);
    }

    @Test
    void generarMensual_DeberiaRetornarReporteConDatos() {
        FiltroReporteMensualDTO filtro = new FiltroReporteMensualDTO();
        filtro.setAnio(2026);
        filtro.setMes(6);

        when(reportesRepository.findByAnioTramiteAndMesTramite(2026, 6)).thenReturn(List.of(modelo));

        ReporteAduaneroResponseDTO resultado = reportesService.generarMensual(filtro);

        assertNotNull(resultado);
        assertTrue(resultado.getTituloReporte().contains("6 / 2026"));
        assertEquals(1, resultado.getDetalleOperaciones().size());
        assertEquals("$1500.0", resultado.getDetalleOperaciones().get(0).getTotalPagado());
    }

    @Test
    void generarAnual_DeberiaRetornarReporteAnual() {
        FiltroReporteAnualDTO filtro = new FiltroReporteAnualDTO();
        filtro.setAnio(2026);

        when(reportesRepository.findByAnioTramite(2026)).thenReturn(List.of(modelo));

        ReporteAduaneroResponseDTO resultado = reportesService.generarAnual(filtro);

        assertNotNull(resultado);
        assertTrue(resultado.getTituloReporte().contains("2026"));
        verify(reportesRepository, times(1)).findByAnioTramite(2026);
    }
}
