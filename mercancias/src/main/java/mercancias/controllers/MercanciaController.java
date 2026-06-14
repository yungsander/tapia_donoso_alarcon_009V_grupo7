package mercancias.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mercancias.models.MercanciaModels;
import mercancias.services.MercanciaService;

@Tag(name = "mercancia", description = "gestion de mercancias del sistema aduanero")
@RestController
@RequestMapping("/api/v1/mercancia")
public class MercanciaController {

    private final MercanciaService mercanciaService;

    public MercanciaController(MercanciaService mercanciaService) {
        this.mercanciaService = mercanciaService;
    }

    // LISTAR MERCANCIAS
    @Operation(summary = "listar todas las mercancias")
    @GetMapping
    public List<MercanciaModels> listar() {
        return mercanciaService.obtenerTodos();
    }

    // REGISTRAR MERCANCIA
    @Operation(summary = "registrar nueva mercancia")
    @PostMapping
    public ResponseEntity<MercanciaModels> guardar(@RequestBody MercanciaModels mercancia) {
        return ResponseEntity.ok(
        mercanciaService.guardar(mercancia)
        );
    }

    // ACTUALIZAR INFORMACION
    @Operation(summary = "actualizar mercancia")
    @PutMapping("/{id}")
    public ResponseEntity<MercanciaModels> actualizar(@PathVariable Long id,@RequestBody MercanciaModels mercancia) {

        MercanciaModels actualizada =
                mercanciaService.actualizar(id, mercancia);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizada);
    }

    // ELIMINAR MERCANCIA
    @Operation(summary = "eliminar mercancia")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mercanciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
