package paises.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import paises.dto.response.PaisesResponseDTO;
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
    public ResponseEntity<List<PaisesResponseDTO>> listar() {
        List<PaisesResponseDTO> listaDTO = service.listarTodos().stream()
            .map(p -> new PaisesResponseDTO(p.getId(), p.getCodigoIso(), p.getNombre()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "obtener pais por id")
    public ResponseEntity<PaisesResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.ObtenerPorId(id));
    }
}
