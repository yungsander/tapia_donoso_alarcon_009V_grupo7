package historial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import historial.dto.request.HistorialRequest;
import historial.dto.response.HistorialResponse;
import historial.services.HistorialServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.*;

@Tag(name = "historial", description = ("historial de accesos de usuarios al sistema"))
@RestController
@RequestMapping("/api/v1/historiales")
public class HistorialController {

    private final HistorialServices historialServices;

    public HistorialController(HistorialServices historialServices) {
        this.historialServices = historialServices;
    }
    
    @Operation(summary = "listar todos los registros")
    @GetMapping
    public ResponseEntity<List<HistorialResponse>> listar() {
        return ResponseEntity.ok(historialServices.obtenerTodos());
    }

    @Operation(summary = "obtener historial por usuario")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<HistorialResponse>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(historialServices.obtenerPorUsuario(idUsuario));
    }

    @Operation(summary = "registrar nuevo acceso")
    @PostMapping
    public ResponseEntity<HistorialResponse> guardar(@Valid @RequestBody HistorialRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historialServices.guardar(request));
    }

    @Operation(summary = "actualizar registro")
    @PutMapping("/{id}")
    public ResponseEntity<HistorialResponse> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody HistorialRequest request
    ) {
        return ResponseEntity.ok(historialServices.actualizar(id, request));
    }
}
