package alertas.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import clients.MercanciaClient;
import dtos.request.AlertRequest;
import dtos.response.MercanciaResponse;
import exceptions.NotFoundException;
import models.AlertModel;
import repositories.AlertRepository;
import services.AlertService;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {
    
    @Mock
    private AlertRepository alertRepository;

    @Mock
    private MercanciaClient mercanciaClient;

    @InjectMocks
    private AlertService alertService;

    private AlertModel alertModel;
    private AlertRequest request;
    private MercanciaResponse mercanciaResponse;

    @BeforeEach
    void setUp() {
        mercanciaResponse = new MercanciaResponse();
        mercanciaResponse.setId(1L);
        mercanciaResponse.setNombre("mercancia test");

        request = new AlertRequest();
        request.setIdMercancia(1L);
        request.setNombreAlerta("retencion");
        request.setMensajeAlerta("mercancia retenida en aduana");

        alertModel = new AlertModel();
        alertModel.setId(1L);
        alertModel.setIdMercancia(1L);
        alertModel.setNombreAlerta("retencion");
        alertModel.setMensajeAlerta("mercancia retenida en aduana");
    }

    @Test
    void obtenerTodas_DeberiaRetornarListaConMercancias() {
        when(alertRepository.findAll()).thenReturn(List.of(alertModel));
        when(mercanciaClient.obtenerMercanciasPorIds(anyList())).thenReturn(List.of(mercanciaResponse));

        var resultado = alertService.obtenerTodas();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("mercancia test", resultado.get(0).getMercancia().getNombre());
    }

    @Test
    @SuppressWarnings({ "null" })
    void guardar_DeberiaRetornarAlertaEnriquecida() {
        when(mercanciaClient.obtenerMercanciaPorId(1L)).thenReturn(mercanciaResponse);
        when(alertRepository.save(any(AlertModel.class))).thenReturn(alertModel);

        var response = alertService.guardar(request);

        assertNotNull(response);
        assertEquals("retencion", response.getNombreAlerta());
        verify(alertRepository).save(any(AlertModel.class));
    }

    @Test
    void obtenerPorId_DeberiaLanzarNotFound_CuandoNoExiste() {
        when(alertRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> alertService.obtenerPorId(99L));
    }
    
}
