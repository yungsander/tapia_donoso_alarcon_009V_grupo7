package documentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import documentos.service.DocumentosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "documentos", description = "gestion de documentos aduaneros")
@RestController
@RequestMapping("/api/documentos")
public class DocumentosController {

    private final DocumentosService documentosService;

    public DocumentosController(DocumentosService documentosService) {
        this.documentosService = documentosService;
    }

    @Operation(summary = "subir documento aduanero")
    @PostMapping("/upload")
    public ResponseEntity<String> subir(
            @RequestParam("file") MultipartFile file,
            @RequestParam("manifiesto") String manifiesto) {
        documentosService.guardarDocumento(file, manifiesto);
        return ResponseEntity.ok("archivo guardado con exito");
                                        }
}
