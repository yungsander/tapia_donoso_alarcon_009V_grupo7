package alertas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import alertas.clients.MercanciaClient;
import alertas.dtos.request.AlertRequest;
import alertas.dtos.response.AlertResponse;
import alertas.dtos.response.MercanciaResponse;
import alertas.exceptions.NotFoundException;
import alertas.exceptions.RemoteServiceException;
import alertas.models.AlertModel;
import alertas.repositories.AlertRepository;

import feign.FeignException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AlertService {

    private final AlertRepository alertRepository;
    private final MercanciaClient mercanciaClient;

    public AlertService(
            AlertRepository alertRepository,
            MercanciaClient mercanciaClient
    ) {
        this.alertRepository = alertRepository;
        this.mercanciaClient = mercanciaClient;
    }

    // Lista todas las alertas
    public List<AlertResponse> obtenerTodas() {

        return alertRepository.findAll()
                .stream()
                .map(this::mapToResponseConMercancia)
                .toList();
    }

    // Busca alerta por ID
    public AlertResponse obtenerPorId(Long id) {

        AlertModel alerta = alertRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "No existe la alerta con id: " + id));

        return mapToResponseConMercancia(alerta);
    }

    // Guarda nueva alerta
    public AlertResponse guardar(AlertRequest request) {

        // Verifica que mercancía exista
        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(request.getIdMercancia());

        AlertModel alerta = new AlertModel();

        alerta.setNombreAlerta(request.getNombreAlerta());
        alerta.setMensajeAlerta(request.getMensajeAlerta());
        alerta.setIdMercancia(request.getIdMercancia());

        AlertModel guardada =
                alertRepository.save(alerta);

        return mapToResponse(guardada, mercancia);
    }

    // Actualiza alerta
    public AlertResponse actualizar(
            Long id,
            AlertRequest request
    ) {

        AlertModel alerta =
                alertRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No existe la alerta con id: " + id));

        // Verifica mercancía
        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(request.getIdMercancia());

        alerta.setNombreAlerta(request.getNombreAlerta());
        alerta.setMensajeAlerta(request.getMensajeAlerta());
        alerta.setIdMercancia(request.getIdMercancia());

        AlertModel actualizada =
                alertRepository.save(alerta);

        return mapToResponse(actualizada, mercancia);
    }

    // Elimina alerta
    public void eliminar(Long id) {

        AlertModel alerta =
                alertRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No existe la alerta con id: " + id));

        alertRepository.delete(alerta);
    }

    // Obtiene mercancía desde microservicio
    private MercanciaResponse obtenerMercanciaDesdeServicio(
            Long idMercancia
    ) {

        try {

            return mercanciaClient
                    .obtenerMercanciaPorId(idMercancia);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe la mercancía con id: " + idMercancia);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de mercancías");
        }
    }

    // Convierte entidad + mercancía
    private AlertResponse mapToResponseConMercancia(
            AlertModel alerta
    ) {

        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(
                        alerta.getIdMercancia());

        return mapToResponse(alerta, mercancia);
    }

    // DTO final
    private AlertResponse mapToResponse(
            AlertModel alerta,
            MercanciaResponse mercancia
    ) {

        return AlertResponse.builder()
                .id(alerta.getId())
                .nombreAlerta(alerta.getNombreAlerta())
                .mensajeAlerta(alerta.getMensajeAlerta())
                .mercancia(mercancia)
                .build();
    }
}
