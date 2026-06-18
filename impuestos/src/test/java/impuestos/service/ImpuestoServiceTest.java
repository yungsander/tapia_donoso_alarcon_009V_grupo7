package impuestos.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import impuestos.clients.MercanciaClient;
import impuestos.dtos.request.ImpuestoRequest;
import impuestos.dtos.response.MercanciaResponse;
import impuestos.exceptions.NotFoundException;
import impuestos.models.ImpuestoModel;
import impuestos.repositories.ImpuestoRepository;
import impuestos.services.ImpuestoService;
import feign.FeignException;

@ExtendWith(MockitoExtension.class)
public class ImpuestoServiceTest {

    @Mock
    private ImpuestoRepository repository;

    @Mock
    private MercanciaClient mercanciaClient;

    @InjectMocks
    private ImpuestoService service;

    private ImpuestoModel model;
    private ImpuestoRequest request;

    @BeforeEach
    void setUp() {
        model = new ImpuestoModel();
        model.setId(1L);
        model.setTasaImpuesto(10.0);

        request = new ImpuestoRequest();
        request.setIdMercancia(1L);
        request.setTasaImpuesto(10.0);
    }

    @Test
    void guardar_DeberiaCalcularMontoYGuardar() {
        //given, prepara los datos
        var request = new ImpuestoRequest();
        request.setTasaImpuesto(10.0);
        request.setIdMercancia(1L);

        var mercancia = new MercanciaResponse();
        mercancia.setValor(1000.0);
        
        //simulamos respuesta del cliente externo i guardamos n bd
        when(mercanciaClient.obtenerMercanciaPorId(1L)).thenReturn(mercancia);
        when(repository.save(any(ImpuestoModel.class))).thenAnswer(i -> i.getArguments()[0]);

        //when ejecucion
        var resultado = service.guardar(request);

        //then verificacion, el 10% de 1000 es 100
        assertNotNull(resultado);
        assertEquals(100.0, resultado.getMontoCalculado());
        verify(repository, times(1)).save(any(ImpuestoModel.class));
    }

    @Test
    void guardar_DeberiaLanzarNotFound_CuandoMercanciaFalla() {
        //given, simulamos q MercanciaClient lanza un 404
        when(mercanciaClient.obtenerMercanciaPorId(1L))
            .thenThrow(mock(FeignException.NotFound.class));

        var request = new ImpuestoRequest();
        request.setIdMercancia(1L);

        //then verificamos q nuestro microservicio traduce el error de feign a nuestra NotFoundException
        assertThrows(NotFoundException.class, () -> service.guardar(request));
    }
}
