package impuestos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import impuestos.dtos.request.ImpuestoRequest;
import impuestos.dtos.response.ImpuestoResponse;
import impuestos.services.ImpuestoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "impuestos", description = "gestion de impuestos aduaneros")
@RestController
@RequestMapping("/api/v1/impuestos")
@Validated
public class ImpuestoController {

    private final ImpuestoService impuestoService;

    public ImpuestoController(
            ImpuestoService impuestoService
    ) {
        this.impuestoService = impuestoService;
    }

    // Lista todos los impuestos
    @Operation(summary = "listar todos los impuestos")
    @GetMapping
    public List<ImpuestoResponse> obtenerTodos() {

        return impuestoService.obtenerTodos();
    }

    // Busca por ID
    @Operation(summary = "obtener impuesto por ID")
    @GetMapping("/{id}")
    public ImpuestoResponse obtenerPorId(
            @PathVariable Long id
    ) {

        return impuestoService.obtenerPorId(id);
    }

    // Registra nuevo impuesto
    @Operation(summary = "registrar nuevo impuesto")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImpuestoResponse guardar(
            @Valid @RequestBody ImpuestoRequest request
    ) {

        return impuestoService.guardar(request);
    }

    // Actualiza impuesto
    @Operation(summary = "actualizar impuesto")
    @PutMapping("/{id}")
    public ImpuestoResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ImpuestoRequest request
    ) {

        return impuestoService.actualizar(id, request);
    }

    // Elimina impuesto
    @Operation(summary = "eliminar impuesto")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable Long id
    ) {

        impuestoService.eliminar(id);
    }
}
