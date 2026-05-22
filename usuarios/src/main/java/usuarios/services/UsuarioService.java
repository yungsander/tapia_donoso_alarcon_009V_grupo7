package usuarios.services;

import java.util.List;

import org.springframework.stereotype.Service;

import usuarios.dtos.request.UsuarioRequest;
import usuarios.dtos.response.UsuarioResponse;
import usuarios.exceptions.DuplicateEmailException;
import usuarios.exceptions.NotFoundException;
import usuarios.models.UsuarioModel;
import usuarios.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository
    ) {
        this.usuarioRepository = usuarioRepository;
    }

    // Lista todos los usuarios
    public List<UsuarioResponse> obtenerTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Busca usuario por ID
    public UsuarioResponse obtenerPorId(Long id) {

        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "No existe el usuario con id: " + id));

        return mapToResponse(usuario);
    }

    // Registra nuevo usuario
    public UsuarioResponse guardar(UsuarioRequest request) {

        // Verifica que el email no esté ya registrado
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(
                    "Ya existe un usuario registrado con el email: " + request.getEmail());
        }

        UsuarioModel usuario = new UsuarioModel();

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setEstado(request.getEstado());

        UsuarioModel guardado =
                usuarioRepository.save(usuario);

        return mapToResponse(guardado);
    }

    // Actualiza usuario existente
    public UsuarioResponse actualizar(
            Long id,
            UsuarioRequest request
    ) {

        UsuarioModel usuario =
                usuarioRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No existe el usuario con id: " + id));

        // Verifica que el nuevo email no pertenezca a otro usuario
        if (!usuario.getEmail().equals(request.getEmail())
                && usuarioRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(
                    "Ya existe un usuario registrado con el email: " + request.getEmail());
        }

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setEstado(request.getEstado());

        UsuarioModel actualizado =
                usuarioRepository.save(usuario);

        return mapToResponse(actualizado);
    }

    // Elimina usuario
    public void eliminar(Long id) {

        UsuarioModel usuario =
                usuarioRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No existe el usuario con id: " + id));

        usuarioRepository.delete(usuario);
    }

    // DTO final
    private UsuarioResponse mapToResponse(UsuarioModel usuario) {

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .estado(usuario.getEstado())
                .build();
    }
}
