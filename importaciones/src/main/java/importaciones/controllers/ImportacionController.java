package mercancias.importaciones.controllers;

import jakarta.validation.Valid;
import mercancias.importaciones.dtos.request.ImportacionRequest;
import mercancias.importaciones.dtos.response.ImportacionResponse;
import mercancias.importaciones.services.ImportacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/importaciones")
public class ImportacionController {

    private final ImportacionService importacionService;

    public ImportacionController(ImportacionService importacionService) {
        this.importacionService = importacionService;
    }

    @GetMapping
    public ResponseEntity<List<ImportacionResponse>> obtenerTodas() {

        return ResponseEntity.ok(importacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImportacionResponse> obtenerPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(importacionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ImportacionResponse> guardar(
            @Valid @RequestBody ImportacionRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(importacionService.guardar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImportacionResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ImportacionRequest request
    ) {

        return ResponseEntity.ok(
                importacionService.actualizar(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {

        importacionService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}