package mercancias.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mercancias.models.MercanciaModels;
import mercancias.repositories.MercanciaRepository;

@ExtendWith(MockitoExtension.class)
public class MercanciaServiceTest {

    @Mock
    private MercanciaRepository mercanciaRepository;

    @InjectMocks
    private MercanciaService mercanciaService;

    private MercanciaModels mercanciaPrueba;

    @BeforeEach
    void setUp() {
        //mercancia de prueba inicial
        mercanciaPrueba = new MercanciaModels();
        mercanciaPrueba.setNombre("laptops de importacion");
        mercanciaPrueba.setTipo("electronica");
        mercanciaPrueba.setPeso(50.5);
        mercanciaPrueba.setValor(1500.0);
        mercanciaPrueba.setPaisOrigen("China");
    }

    @Test
    void obtenerTodos_DeberiaRetornarListaDeMercancias() {
        //given
        List<MercanciaModels> listaEsperada = Arrays.asList(mercanciaPrueba);
        when(mercanciaRepository.findAll()).thenReturn(listaEsperada);

        //when
        List<MercanciaModels> resultado = mercanciaService.obtenerTodos();

        //then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("laptops de importacion", resultado.get(0).getNombre());
        verify(mercanciaRepository, times(1)).findAll();
    }

    @Test
    void guardar_DeberiaRetornarMercanciaGuardada() {
        //given
        when(mercanciaRepository.save(any(MercanciaModels.class))).thenReturn(mercanciaPrueba);

        //when
        MercanciaModels resultado = mercanciaService.guardar(mercanciaPrueba);

        //then
        assertNotNull(resultado);
        assertEquals("laptops de importacion", resultado.getNombre());
        assertEquals("electronica", resultado.getTipo());
        verify(mercanciaRepository, times(1)).save(any(MercanciaModels.class));
    }

    @Test
    void actualizar_DeberiaRetornarMercanciaActualizada_CuandoExiste() {
        //given
        Long id = 1L;

        MercanciaModels mercanciaActualizada = new MercanciaModels();
        mercanciaActualizada.setNombre("laptops actualizadas");
        mercanciaActualizada.setTipo("tecnologia");
        mercanciaActualizada.setPeso(60.0);
        mercanciaActualizada.setValor(2000.0);
        mercanciaActualizada.setPaisOrigen("Japon");

        when(mercanciaRepository.findById(id)).thenReturn(Optional.of(mercanciaPrueba));
        when(mercanciaRepository.save(any(MercanciaModels.class))).thenReturn(mercanciaActualizada);

        //when
        MercanciaModels resultado = mercanciaService.actualizar(id, mercanciaActualizada);

        //then
        assertNotNull(resultado);
        assertEquals("laptops actualizadas", resultado.getNombre());
        assertEquals(2000.0, resultado.getValor());

        verify(mercanciaRepository, times(1)).findById(id);
        verify(mercanciaRepository, times(1)).save(any(MercanciaModels.class));
    }

    @Test
    void actualizar_DeberiaRetornarNull_CuandoNoExiste() {
        //given
        Long idInexistente = 99L;
        
        MercanciaModels mercanciaActualizada = new MercanciaModels();
        mercanciaActualizada.setNombre("no importa");

        when(mercanciaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        //when
        MercanciaModels resultado = mercanciaService.actualizar(idInexistente, mercanciaActualizada);

        //then
        assertNull(resultado, "el resultado debe ser null si la mercancia no existe");

        verify(mercanciaRepository, times(1)).findById(idInexistente);
        verify(mercanciaRepository, never()).save(any());
    }

    @Test
    void eliminar_DeberiaLlamarAlMetodoDeleteById() {
        // given
        Long idAEliminar = 1L;

        // when
        mercanciaService.eliminar(idAEliminar);
        
        // then
        verify(mercanciaRepository, times(1)).deleteById(idAEliminar);
    }

}
