package exportaciones.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import exportaciones.dtos.request.ExportacionRequest;
import exportaciones.dtos.response.ExportacionResponse;
import exportaciones.services.ExportacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "exportaciones", description = "gestion de exportaciones aduaneras")
@RestController
@RequestMapping("/api/v1/exportaciones")
@Validated
public class ExportacionController {

    private final ExportacionService exportacionService;

    public ExportacionController(
            ExportacionService exportacionService
    ) {
        this.exportacionService = exportacionService;
    }

    // Lista todas
    @Operation(summary = "listar todas las exportaciones")
    @GetMapping
    public List<ExportacionResponse> obtenerTodas() {

        return exportacionService.obtenerTodas();
    }

    // Busca por ID
    @Operation(summary = "obtener exportacion por ID")
    @GetMapping("/{id}")
    public ExportacionResponse obtenerPorId(
            @PathVariable Long id
    ) {

        return exportacionService.obtenerPorId(id);
    }

    // Guarda
    @Operation(summary = "registrar nueva exportacion")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExportacionResponse guardar(
            @Valid @RequestBody ExportacionRequest request
    ) {

        return exportacionService.guardar(request);
    }

    // Actualiza
    @Operation(summary = "actualizar exportacion")
    @PutMapping("/{id}")
    public ExportacionResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ExportacionRequest request
    ) {

        return exportacionService.actualizar(id, request);
    }

    // Elimina
    @Operation(summary = "eliminar exportacion")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable Long id
    ) {

        exportacionService.eliminar(id);
    }
}