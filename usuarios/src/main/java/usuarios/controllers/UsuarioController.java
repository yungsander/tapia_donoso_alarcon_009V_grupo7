package usuarios.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import usuarios.dtos.request.UsuarioRequest;
import usuarios.dtos.response.UsuarioResponse;
import usuarios.services.UsuarioService;

import jakarta.validation.Valid;

@Tag(name = "usuarios", description = "gestion de usuarios del sistema aduanero")
@RestController
@RequestMapping("/api/v1/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Lista todos los usuarios
    @Operation(summary = "listar todos los usuarios",
        description = "retorna una lista con todos los usuarios registrados")
    @GetMapping
    public List<UsuarioResponse> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    // Busca por ID
    @Operation(summary = "obtener usuario por ID",
        description = "busca un usuario especifico mediante su id unico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "usuario no encontrado")
    })
    @GetMapping("/{id}")
    public UsuarioResponse obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    // Registra nuevo usuario
    @Operation(summary = "registrar nuevo usuario",
        description = "crea un nuevo usuario validando que el email no esté duplicado")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "usuario creado"),
        @ApiResponse(responseCode = "400", description = "email duplicado o datos invalidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse guardar(@Valid @RequestBody UsuarioRequest request) {
        return usuarioService.guardar(request);
    }

    // Actualiza usuario
    @Operation(summary = "actualizar usuario",
        description = "modifica los datos de un usuario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "usuario actualizado"),
        @ApiResponse(responseCode = "404", description = "usuario no encontrado")
    })
    @PutMapping("/{id}")
    public UsuarioResponse actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        return usuarioService.actualizar(id, request);
    }

    // Elimina usuario
    @Operation(summary = "eliminar usuario",
        description = "elimina permanentemente un usuario del sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
