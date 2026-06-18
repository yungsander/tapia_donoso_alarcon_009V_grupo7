package importaciones.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import feign.FeignException;
import importaciones.clients.MercanciaClient;
import importaciones.dtos.request.ImportacionRequest;
import importaciones.dtos.response.ImportacionResponse;
import importaciones.dtos.response.MercanciaResponse;
import importaciones.exceptions.NotFoundException;
import importaciones.models.ImportacionModel;
import importaciones.repositories.ImportacionRepository;
import importaciones.services.ImportacionService;

@ExtendWith(MockitoExtension.class)
public class ImportacionServiceTest {

    @Mock
    private ImportacionRepository importacionRepository;

    @Mock
    private MercanciaClient mercanciaClient;

    @InjectMocks
    private ImportacionService importacionService;

    private ImportacionModel importacionModel;
    private ImportacionRequest request;
    private MercanciaResponse mercanciaResponse;

    @BeforeEach
    void setUp() {
        mercanciaResponse = new MercanciaResponse();
        mercanciaResponse.setId(100L);
        mercanciaResponse.setPrecio(5000.0);

        request = new ImportacionRequest();
        request.setIdMercancia(100l);
        request.setPaisOrigen("Japon");
        request.setPeso(120.5);
        request.setTipoMercancia("Electronica");
        request.setEstado("en_transito");

        importacionModel = new ImportacionModel();
        importacionModel.setId(1L);
        importacionModel.setIdMercancia(100L);
        importacionModel.setPaisOrigen("japon");
        importacionModel.setPeso(120.5);
        importacionModel.setTipoMercancia("electronica");
        importacionModel.setEstado("en_transito");
    }

    @Test
    void guardar_DeberiaGuardarYRetornarResponse_CuandoMercanciaExiste() {
        //simula q feign encuentra la mercancia
        when(mercanciaClient.obtenerMercanciaPorId(100L)).thenReturn(mercanciaResponse);
        //simula q el repositorio guarda correctamente
        when(importacionRepository.save(any(ImportacionModel.class))).thenReturn(importacionModel);

        ImportacionResponse response = importacionService.guardar(request);

        assertNotNull(response);
        assertEquals("japon", response.getPaisOrigen());
        //verificamos q al dto final le llegaron datos d feign
        assertNotNull(response.getMercancia());

        //verificamos interacciones
        verify(mercanciaClient, times(1)).obtenerMercanciaPorId(100L);
        verify(importacionRepository, times(1)).save(any(ImportacionModel.class));
    }

    @Test
    void guardar_DeberiaLanzarNotFound_CuandoMercanciaNoExiste() {
        //simulamos q el servicio d mercancias responde cn 404
        when(mercanciaClient.obtenerMercanciaPorId(100L))
            .thenThrow(mock(FeignException.NotFound.class));
        
        //validamos q nuestra excepcion personalizada captura el error de feign
        assertThrows(NotFoundException.class, () -> importacionService.guardar(request));

        //aseguramos q no se guardó nada en la bd si la mercancia no existe
        verify(importacionRepository, never()).save(any());
    }

    @Test
    void obtenerPorId_DeberiaRetornarImportacionEnriquecida() {
        when(importacionRepository.findById(1L)).thenReturn(Optional.of(importacionModel));
        when(mercanciaClient.obtenerMercanciaPorId(100L)).thenReturn(mercanciaResponse);

        ImportacionResponse response = importacionService.obtenerPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(100L, response.getMercancia().getId());
    }
    
}
