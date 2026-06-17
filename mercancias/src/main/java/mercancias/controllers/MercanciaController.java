package mercancias.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mercancias.models.MercanciaModels;
import mercancias.services.MercanciaService;

import java.util.List;

@Tag(name = "mercancias", description = "gestion de mercancias del sistema aduanero")
@RestController
@RequestMapping("/api/mercancias")
public class MercanciaController {

    private final MercanciaService mercanciaService;

    public MercanciaController(MercanciaService mercanciaService) {
        this.mercanciaService = mercanciaService;
    }

    // LISTAR MERCANCIAS
    @Operation(summary = "listar todas las mercancias",
        description = "retorna una lista completa de todas las mercancias registradas en el sistema de aduanas")
    @ApiResponse(responseCode = "200", description = "lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<MercanciaModels>> obtenerTodos() {
        return ResponseEntity.ok(mercanciaService.obtenerTodos());
    }

    // REGISTRAR MERCANCIA
    @Operation(summary = "registrar nueva mercancia",
        description = "guarda una nueva mercancia en la base de datos con sus respectivos detalles (peso, valor, origen, etc)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "mercancia creada y guardada existosamente"),
        @ApiResponse(responseCode = "400", description = "datos de entrada invalidos o incompletos")
    })    
    @PostMapping
    public ResponseEntity<MercanciaModels> guardar(@RequestBody MercanciaModels mercancia) {
        MercanciaModels nuevaMercancia = mercanciaService.guardar(mercancia);
        return new ResponseEntity<>(nuevaMercancia, HttpStatus.CREATED);
    }

    // ACTUALIZAR INFORMACION
    @Operation(summary = "actualizar mercancia")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "mercancia actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "no se encontro una mercancia con el id proporcionado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MercanciaModels> actualizar(@PathVariable Long id, @RequestBody MercanciaModels mercancia) {
        MercanciaModels mercanciaActualizada = mercanciaService.actualizar(id, mercancia);
        if (mercanciaActualizada != null) {
            return ResponseEntity.ok(mercanciaActualizada)
        }
        return ResponseEntity.notFound().build();
    }

    // ELIMINAR MERCANCIA
    @Operation(summary = "eliminar mercancia")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "mercancia eliminada existosamente (sin contenido"),
        @ApiResponse(responseCode = "404", description = "mercancia no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mercanciaService.eliminar(id);
        return ResponseEntity.noContent().build(); 
}
