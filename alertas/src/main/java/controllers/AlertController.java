package alertas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import alertas.dtos.request.AlertRequest;
import alertas.dtos.response.AlertResponse;
import alertas.services.AlertService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/alertas")
@Validated
public class AlertController {

    private final AlertService alertService;

    public AlertController(
            AlertService alertService
    ) {
        this.alertService = alertService;
    }

    // Lista todas
    @GetMapping
    public List<AlertResponse> obtenerTodas() {

        return alertService.obtenerTodas();
    }

    // Busca por ID
    @GetMapping("/{id}")
    public AlertResponse obtenerPorId(
            @PathVariable Long id
    ) {

        return alertService.obtenerPorId(id);
    }

    // Guarda
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlertResponse guardar(
            @Valid @RequestBody AlertRequest request
    ) {

        return alertService.guardar(request);
    }

    // Actualiza
    @PutMapping("/{id}")
    public AlertResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlertRequest request
    ) {

        return alertService.actualizar(id, request);
    }

    // Elimina
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable Long id
    ) {

        alertService.eliminar(id);
    }
}
