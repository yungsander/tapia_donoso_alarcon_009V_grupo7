package exportaciones.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import feign.FeignException;
import exportaciones.clients.MercanciaClient;
import exportaciones.dtos.request.ExportacionRequest;
import exportaciones.dtos.response.ExportacionResponse;
import exportaciones.dtos.response.MercanciaResponse;
import exportaciones.exceptions.NotFoundException;
import exportaciones.models.ExportacionModel;
import exportaciones.repositories.ExportacionRepository;
import exportaciones.services.ExportacionService;

@ExtendWith(MockitoExtension.class)
public class ExportacionServiceTest {

    @Mock
    private ExportacionRepository exportacionRepository;

    @Mock
    private MercanciaClient mercanciaClient;

    @InjectMocks
    private ExportacionService exportacionService;

    private ExportacionModel exportacionModel;
    private ExportacionRequest request;
    private MercanciaResponse mercanciaResponse;

    @BeforeEach
    void setUp() {
        //respuesta simulada a feign
        mercanciaResponse = new MercanciaResponse();
        mercanciaResponse.setId(200l);
        mercanciaResponse.setValor(8500.0);

        //request de entrada
        request = new ExportacionRequest();
        request.setIdMercancia(200L);
        request.setPaisDestino("alemania");
        request.setPeso(500.0);
        request.setTipoMercancia("minerales");
        request.setEstado("autorizada");

        //modelo simulado a bd
        exportacionModel = new ExportacionModel();
        exportacionModel.setId(1L);
        exportacionModel.setIdMercancia(200L);
        exportacionModel.setPaisDestino("alemania");
        exportacionModel.setPeso(500.0);
        exportacionModel.setTipoMercancia("minerales");
        exportacionModel.setEstado("autorizada");
    }

    @Test
    void guardar_DeberiaGuardarYRetornarResponse_CuandoMercanciaExiste() {
        when(mercanciaClient.obtenerMercanciaPorId(200L)).thenReturn(mercanciaResponse);
        when(exportacionRepository.save(any(ExportacionModel.class))).thenReturn(exportacionModel);

        ExportacionResponse response = exportacionService.guardar(request);

        assertNotNull(response);
        assertEquals("alemania", response.getPaisDestino());
        assertNotNull(response.getMercancia());

        verify(mercanciaClient, times(1)).obtenerMercanciaPorId(200L);
        verify(exportacionRepository, times(1)).save(any(ExportacionModel.class));
    }

    @Test
    void guardar_DeberiaLanzarNotFound_CuandoMercanciaNoExiste() {
        when(mercanciaClient.obtenerMercanciaPorId(200L))
            .thenThrow(mock(FeignException.NotFound.class));

        assertThrows(NotFoundException.class, () -> exportacionService.guardar(request));
        verify(exportacionRepository, never()).save(any());
    }
    
}
