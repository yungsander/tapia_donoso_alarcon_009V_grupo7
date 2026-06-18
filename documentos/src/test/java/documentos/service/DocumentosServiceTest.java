package documentos.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import documentos.exception.NotFoundException;
import documentos.model.DocumentosModel;
import documentos.repository.DocumentosRepository;

@ExtendWith(MockitoExtension.class)
public class DocumentosServiceTest {

    @Mock
    private DocumentosRepository repository;

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private DocumentosService documentosService;

    private DocumentosModel documentosModel;
    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        mockFile = new MockMultipartFile(
            "file",
            "factura_comercial.pdf",
            "application/pdf",
            "contenido del archivo dummy".getBytes()    
        );

        documentosModel = new DocumentosModel();
        documentosModel.setNumeroManifiesto("MAN-12345");
        documentosModel.setNombreOriginal("factura_comercial.pdf");
        documentosModel.setTipoArchivo("application/pdf");
        documentosModel.setRutaAlmacenamiento("uuid-8899_factura_comercial.pdf");
    }
    
    @Test
    void guardarDocumento_DeberiaGuardarExitosamente() {
        //le decimos a nuestro mock del disco duro q retorne un nombre d archivo
        when(fileStorageService.guardarArchivo(any(MultipartFile.class)))
            .thenReturn("uuid-8899_factura_comercial.pdf");
        //le decimos al repositorio q guarde el modelo
        when(repository.save(any(DocumentosModel.class))).thenReturn(documentosModel);
        //ejecutamos el metodo
        DocumentosModel resultado = documentosService.guardarDocumento(mockFile, "MAN-12345");

        //validaciones
        assertNotNull(resultado);
        assertEquals("MAN-12345", resultado.getNumeroManifiesto());
        assertEquals("uuid-8899_factura_comercial.pdf", resultado.getRutaAlmacenamiento());

        //verificamos q se llamó al disco duro y a la bd
        verify(fileStorageService, times(1)).guardarArchivo(any(MultipartFile.class));
        verify(repository, times(1)).save(any(DocumentosModel.class));
    }

    @Test
    void obtenerPorManifiesto_DeberiaRetornarLista() {
        when(repository.findByNumeroManifiesto("MAN-12345")).thenReturn(List.of(documentosModel));

        List<DocumentosModel> resultado = documentosService.obtenerPorManifiesto("MAN-12345");

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("MAN-12345", resultado.get(0). getNumeroManifiesto());
    }

    @Test
    void obtenerPorManifiesto_DeberiaLanzarNotFound_CuandoNoExisten() {
        when(repository.findByNumeroManifiesto("MAN-000")).thenReturn(new ArrayList<>());

        assertThrows(NotFoundException.class, () -> documentosService.obtenerPorManifiesto("MAN-000"));
    }
}
