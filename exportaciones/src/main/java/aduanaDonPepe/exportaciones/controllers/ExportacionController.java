package aduanaDonPepe.exportaciones.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import aduanaDonPepe.exportaciones.dtos.request.ExportacionRequest;
import aduanaDonPepe.exportaciones.dtos.response.ExportacionResponse;
import aduanaDonPepe.exportaciones.services.ExportacionService;

import jakarta.validation.Valid;

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
    @GetMapping
    public List<ExportacionResponse> obtenerTodas() {

        return exportacionService.obtenerTodas();
    }

    // Busca por ID
    @GetMapping("/{id}")
    public ExportacionResponse obtenerPorId(
            @PathVariable Long id
    ) {

        return exportacionService.obtenerPorId(id);
    }

    // Guarda
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExportacionResponse guardar(
            @Valid @RequestBody ExportacionRequest request
    ) {

        return exportacionService.guardar(request);
    }

    // Actualiza
    @PutMapping("/{id}")
    public ExportacionResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ExportacionRequest request
    ) {

        return exportacionService.actualizar(id, request);
    }

    // Elimina
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable Long id
    ) {

        exportacionService.eliminar(id);
    }
}