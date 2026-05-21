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
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/v1/historiales")
public class HistorialController {

    private final HistorialServices historialServices;

    public HistorialController(HistorialServices historialServices) {
        this.historialServices = historialServices;
    }
    
    @GetMapping
    public ResponseEntity<List<HistorialResponse>> listar() {
        return ResponseEntity.ok(historialServices.obtenerTodos());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<HistorialResponse>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(historialServices.obtenerPorUsuario(idUsuario));
    }

    @PostMapping
    public ResponseEntity<HistorialResponse> guardar(@Valid @RequestBody HistorialRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historialServices.guardar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialResponse> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody HistorialRequest request
    ) {
        return ResponseEntity.ok(historialServices.actualizar(id, request));
    }
}
