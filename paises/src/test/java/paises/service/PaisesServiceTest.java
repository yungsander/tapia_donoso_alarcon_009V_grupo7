package paises.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import paises.dto.response.PaisesResponseDTO;
import paises.exception.NotFoundException;
import paises.model.PaisesModel;
import paises.repository.PaisesRepository;

@ExtendWith(MockitoExtension.class)
public class PaisesServiceTest {

    @Mock
    private PaisesRepository repository;

    @InjectMocks
    private PaisesService service;

    @Test
    void obtenerPorId_DeberiaRetornarDTO_CuandoElPaisExiste() {
        PaisesModel model = new PaisesModel();
        model.setId(1L);
        model.setCodigoIso("CHL");
        model.setNombre("Chile");

        when(repository.findById(1L)).thenReturn(Optional.of(model));

        PaisesResponseDTO resultado = service.ObtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("CHL", resultado.getCodigoIso());
        assertEquals("Chile", resultado.getNombre());
    }

    @Test
    void obtenerPorId_DeberiaLanzarNotFoundException_CuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.ObtenerPorId(99L));
    }
    
}
