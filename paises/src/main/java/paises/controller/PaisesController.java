package paises.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import paises.model.PaisesModel;
import paises.service.PaisesService;

@Tag(name = "paises", description = "catalogo de paises disponibles en el sistema")
@RestController
@RequestMapping("/api/paises")
public class PaisesController {

    private final PaisesService service;

    public PaisesController(PaisesService service) {
        this.service = service;
    }
    
    @Operation(summary = "listar todos los paises")
    @GetMapping
    public ResponseEntity<List<PaisesModel>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
