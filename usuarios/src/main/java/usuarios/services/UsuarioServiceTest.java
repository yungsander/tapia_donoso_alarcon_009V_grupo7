package usuarios.services;

import usuarios.repositories.UsuarioRepository;
import org.mockito

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void cuandoGuardarUsuario_entoncesExito() {
        Usuario usuario = new Usuario(1L, "Juan", "Juan@donpepe.com");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario guardado = usuarioService.guardar(usuario);

        assertNotNull(guardado);
        assertEquals("juan@donpepe.com", guardado.getEmail());
        verify(usuarioRepository, times(1)).save(usuario);
    }
    
    @Test
}
