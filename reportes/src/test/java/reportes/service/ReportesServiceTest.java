package reportes.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import reportes.dto.request.FiltroReporteDTO;
import reportes.dto.response.ReporteItemDTO;
import reportes.model.ReportesModel;
import reportes.repository.ReportesRepository;

@ExtendWith(MockitoExtension.class)
public class ReportesServiceTest {
    
    @Mock
    private ReportesRepository repository;

    @InjectMocks
    private ReportesService service;
    
    @Test
    void generarReporte_DeberiaMapearCorrectamente() {
        //given
        FiltroReporteDTO filtro = new FiltroReporteDTO();
        filtro.setAnio(2026);

        ReportesModel model = new ReportesModel();
        model.setNombreAgencia("Agencia Norte");
        model.setTotalImpuestosPagados(1000.0);

        //when
        when(repository.findByAnioTramite(2026)).thenReturn(List.of(model));

        //then
        List<ReporteItemDTO> resultado = service.generarReporte(filtro);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Agencia Norte", resultado.get(0).getAgencia());
        assertEquals("$1000.0", resultado.get(0).getTotalPagado());
    }
    
}
