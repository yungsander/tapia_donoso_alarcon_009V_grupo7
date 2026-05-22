package paises.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paises.model.PaisesModel;
import paises.service.PaisesService;

@RestController
@RequestMapping("/api/paises")
public class PaisesController {

    private final PaisesService service;

    public PaisesController(PaisesService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<PaisesModel>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
