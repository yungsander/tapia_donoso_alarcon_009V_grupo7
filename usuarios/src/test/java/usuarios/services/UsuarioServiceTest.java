package usuarios.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import usuarios.dtos.request.UsuarioRequest;
import usuarios.dtos.response.UsuarioResponse;
import usuarios.exceptions.DuplicateEmailException;
import usuarios.exceptions.NotFoundException;
import usuarios.models.UsuarioModel;
import usuarios.repositories.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioModel usuarioModel;
    private UsuarioRequest request;

    @BeforeEach
    void setUp() {
        usuarioModel = new UsuarioModel();
        usuarioModel.setId(1L);
        usuarioModel.setEmail("test@test.com");
        usuarioModel.setNombre("andres");

        request = new UsuarioRequest();
        request.setEmail("test@test.com");
        request.setNombre("andres");
    }

    @Test
    void obtenerTodos_DeberiaRetornarLista() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuarioModel));
        List<UsuarioResponse> resultado = usuarioService.obtenerTodos();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }

    @Test
    void obtenerPorId_DeberiaRetornarUsuario_CuandoExiste() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioModel));
        UsuarioResponse response = usuarioService.obtenerPorId(1L);
        assertNotNull(response);
        assertEquals("test@test.com", response.getEmail());
    }
    
    @Test
    void obtenerPorId_DeberiaLanzarNotFound_CuandoNoExiste() {
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> usuarioService.obtenerPorId(2L));
    }

    @Test
    void guardarDeberiaRetornarUsuario_CuandoEsValido() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(usuarioModel);

        UsuarioResponse response = usuarioService.guardar(request);
        assertNotNull(response);
        verify(usuarioRepository, times(1)).save(any());
    }

    @Test
    void guardar_DeberiaLanzarExcepcion_CuandoEmailDuplicado() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(true);
        assertThrows(DuplicateEmailException.class, () -> usuarioService.guardar(request));
    }
}
