package historial.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import historial.dto.request.HistorialRequest;
import historial.dto.response.HistorialResponse;
import historial.exception.NotFoundException;
import historial.model.HistorialModel;
import historial.repository.HistorialRepository;
import historial.services.HistorialServices;

@ExtendWith(MockitoExtension.class)
public class HistorialServiceTest {

    @Mock
    private HistorialRepository historialRepository;

    @InjectMocks
    private HistorialServices historialServices;

    private HistorialModel historialModel;
    private HistorialRequest request;

    @BeforeEach
    void setUp() {
        // preparamos el request
        request = new HistorialRequest();
        request.setIdUsuario(10L);
        request.setFechaIngreso(LocalDate.now());
        request.setHoraIngreso(LocalTime.now());
        request.setPuntoAcceso("puerta principal aduana");
        request.setEstadoIngreso("autorizado");
        request.setVigente(true);

        //preparamos el modelo simulado
        historialModel = new HistorialModel();
        historialModel.setIdHistorial(1L);
        historialModel.setIdUsuario(10L);
        historialModel.setFechaIngreso(LocalDate.now());
        historialModel.setHoraIngreso(LocalTime.now());
        historialModel.setPuntoAcceso("puerta principal aduana");
        historialModel.setEstadoIngreso("autorizado");
        historialModel.setVigente(true);
    }

    @Test
    void obtenerTodos_DeberiaRetornarLista() {
        when(historialRepository.findAll()).thenReturn(List.of(historialModel));

        List<HistorialResponse> resultado = historialServices.obtenerTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("puerta principal aduana", resultado.get(0).getPuntoAcceso());
    }
    
    @Test
    void guardar_DeberiaGuardarYRetornarResponse() {
        when(historialRepository.save(any(HistorialModel.class))).thenReturn(historialModel);

        HistorialResponse response = historialServices.guardar(request);

        assertNotNull(response);
        assertEquals(10L, response.getIdUsuario());
        assertEquals("autorizado", response.getEstadoIngreso());

        verify(historialRepository, times(1)).save(any(HistorialModel.class));
    }

    @Test
    void actualizar_DeberiaLanzarNotFound_CuandoNoExiste() {
        when(historialRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> historialServices.actualizar(99L, request));
        verify(historialRepository, never()).save(any());
    }

    @Test
    void obtenerPorUsuario_DeberiaRetornarLista() {
        when(historialRepository.findByIdUsuario(10L)).thenReturn(List.of(historialModel));

        List<HistorialResponse> resultado = historialServices.obtenerPorUsuario(10L);

        assertFalse(resultado.isEmpty());
        assertEquals(10L, resultado.get(0).getIdUsuario());
    }
}
