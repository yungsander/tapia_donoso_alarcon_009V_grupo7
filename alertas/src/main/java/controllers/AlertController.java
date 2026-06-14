package controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import dtos.request.AlertRequest;
import dtos.response.AlertResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import services.AlertService;

import jakarta.validation.Valid;

// @tag solo agrupa cada endpoint n swagger x categoria
// en ui aparece como titulo cn cada endp del servicio abajo
@Tag(name = "Alertas", description = "gestion de alertas de mercancias") 
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
    // @operation describe q hace cda endp, aparece cm txto explicado al lado
    // de cada ruta 
    // (sin las anotaciones funciona igual pero aparece sin nombre ni agrupacion, solo las rutas)
    @Operation(summary = "listar todas las alertas")
    @GetMapping
    public List<AlertResponse> obtenerTodas() {

        return alertService.obtenerTodas();
    }

    // Busca por ID

    @GetMapping("/{id}")
    @Operation(summary = "obtener alerta por ID")
    public AlertResponse obtenerPorId(
            @PathVariable Long id
    ) {

        return alertService.obtenerPorId(id);
    }

    // Guarda
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "crear una alerta")
    public AlertResponse guardar(
            @Valid @RequestBody AlertRequest request
    ) {

        return alertService.guardar(request);
    }

    // Actualiza
    @PutMapping("/{id}")
    @Operation(summary = "actualizar alerta")
    public AlertResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlertRequest request
    ) {

        return alertService.actualizar(id, request);
    }

    // Elimina
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "eliminar alerta")
    public void eliminar(
            @PathVariable Long id
    ) {

        alertService.eliminar(id);
    }
}
