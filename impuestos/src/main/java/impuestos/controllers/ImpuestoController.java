package impuestos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import impuestos.dtos.request.ImpuestoRequest;
import impuestos.dtos.response.ImpuestoResponse;
import impuestos.services.ImpuestoService;

import jakarta.validation.Valid;

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
    @GetMapping
    public List<ImpuestoResponse> obtenerTodos() {

        return impuestoService.obtenerTodos();
    }

    // Busca por ID
    @GetMapping("/{id}")
    public ImpuestoResponse obtenerPorId(
            @PathVariable Long id
    ) {

        return impuestoService.obtenerPorId(id);
    }

    // Registra nuevo impuesto
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImpuestoResponse guardar(
            @Valid @RequestBody ImpuestoRequest request
    ) {

        return impuestoService.guardar(request);
    }

    // Actualiza impuesto
    @PutMapping("/{id}")
    public ImpuestoResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ImpuestoRequest request
    ) {

        return impuestoService.actualizar(id, request);
    }

    // Elimina impuesto
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable Long id
    ) {

        impuestoService.eliminar(id);
    }
}
