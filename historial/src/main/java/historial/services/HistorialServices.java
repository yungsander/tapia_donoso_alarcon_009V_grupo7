package historial.services;

import org.springframework.stereotype.Service;

import historial.dto.request.HistorialRequest;
import historial.dto.response.HistorialResponse;
import historial.exception.NotFoundException;
import historial.model.HistorialModel;
import historial.repository.HistorialRepository;
import java.util.*;
import java.util.stream.*;

@Service
public class HistorialServices {
    
    private final HistorialRepository historialRepository;

    public HistorialServices(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public List<HistorialResponse> obtenerTodos() {
        return historialRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<HistorialResponse> obtenerPorUsuario(Long idUsuario) {
        return historialRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<HistorialResponse> obtenerPorVigencia(boolean Vigente) {
        return historialRepository.findByVigente(Vigente)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());

    }

    public HistorialResponse guardar(HistorialRequest request) {
        HistorialModel modelo = new HistorialModel();

        modelo.setIdUsuario(request.getIdUsuario());
        modelo.setFechaIngreso(request.getFechaIngreso());
        modelo.setHoraIngreso(request.getHoraIngreso());
        modelo.setPuntoAcceso(request.getPuntoAcceso());
        modelo.setEstadoIngreso(request.getEstadoIngreso());
        modelo.setVigente(request.isVigente());

        HistorialModel guardado = historialRepository.save(modelo);
        return convertirAResponse(guardado);
    }

    public HistorialResponse actualizar(Long id, HistorialRequest request) {

        HistorialModel existente = historialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("historial no encontrado con el id: " + id));

        existente.setIdUsuario(request.getIdUsuario());
        existente.setFechaIngreso(request.getFechaIngreso());
        existente.setHoraIngreso(request.getHoraIngreso());

        existente.setPuntoAcceso(request.getPuntoAcceso());
        existente.setEstadoIngreso(request.getEstadoIngreso());
        existente.setVigente(request.isVigente());

        HistorialModel actualizado = historialRepository.save(existente);
        return convertirAResponse(actualizado);
    }

    private HistorialResponse convertirAResponse(HistorialModel modelo) {
        HistorialResponse response = new HistorialResponse();
        response.setIdHistorial(modelo.getIdHistorial());
        response.setIdUsuario(modelo.getIdUsuario());
        response.setFechaIngreso(modelo.getFechaIngreso());
        response.setHoraIngreso(modelo.getHoraIngreso());
        response.setFechaSalida(modelo.getFechaSalida());
        response.setHoraSalida(modelo.getHoraSalida());
        response.setPuntoAcceso(modelo.getPuntoAcceso());
        response.setEstadoIngreso(modelo.getEstadoIngreso());
        response.setVigente(modelo.isVigente());
        return response;
    }
}
