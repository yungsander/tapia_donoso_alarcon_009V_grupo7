package importaciones.controllers;

import jakarta.validation.Valid;
import importaciones.dtos.request.ImportacionRequest;
import importaciones.dtos.response.ImportacionResponse;
import importaciones.services.ImportacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "importaciones", description = "gestion de importaciones aduaneras")
@RestController
@RequestMapping("/api/v1/importaciones")
public class ImportacionController {

    private final ImportacionService importacionService;

    public ImportacionController(ImportacionService importacionService) {
        this.importacionService = importacionService;
    }

    // Lista todas las importaciones
    @Operation(summary = "listar todas las importaciones")
    @GetMapping
    public ResponseEntity<List<ImportacionResponse>> obtenerTodas() {

        return ResponseEntity.ok(importacionService.obtenerTodas());
    }

    // Busca importación por ID
    @Operation(summary = "obtener importacion por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ImportacionResponse> obtenerPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(importacionService.obtenerPorId(id));
    }

    // Crea nueva importación
    @Operation(summary = "registrar nueva importacion")
    @PostMapping
    public ResponseEntity<ImportacionResponse> guardar(
            @Valid @RequestBody ImportacionRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(importacionService.guardar(request));
    }

    // Actualiza importación
    @Operation(summary = "actualizar importacion")
    @PutMapping("/{id}")
    public ResponseEntity<ImportacionResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ImportacionRequest request
    ) {

        return ResponseEntity.ok(
                importacionService.actualizar(id, request)
        );
    }

    // Elimina importación
    @Operation(summary = "eliminar importacion")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {

        importacionService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}