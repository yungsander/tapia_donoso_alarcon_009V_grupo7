package usuarios.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import usuarios.dtos.request.UsuarioRequest;
import usuarios.dtos.response.UsuarioResponse;
import usuarios.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(
            UsuarioService usuarioService
    ) {
        this.usuarioService = usuarioService;
    }

    // Lista todos los usuarios
    @GetMapping
    public List<UsuarioResponse> obtenerTodos() {

        return usuarioService.obtenerTodos();
    }

    // Busca por ID
    @GetMapping("/{id}")
    public UsuarioResponse obtenerPorId(
            @PathVariable Long id
    ) {

        return usuarioService.obtenerPorId(id);
    }

    // Registra nuevo usuario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse guardar(
            @Valid @RequestBody UsuarioRequest request
    ) {

        return usuarioService.guardar(request);
    }

    // Actualiza usuario
    @PutMapping("/{id}")
    public UsuarioResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request
    ) {

        return usuarioService.actualizar(id, request);
    }

    // Elimina usuario
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable Long id
    ) {

        usuarioService.eliminar(id);
    }
}
