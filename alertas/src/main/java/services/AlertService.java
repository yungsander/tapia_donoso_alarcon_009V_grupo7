package services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import clients.MercanciaClient;
import dtos.request.AlertRequest;
import dtos.response.AlertResponse;
import dtos.response.MercanciaResponse;
import exceptions.NotFoundException;
import exceptions.RemoteServiceException;
import models.AlertModel;
import repositories.AlertRepository;

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

        List<AlertModel> alertas = alertRepository.findAll();

        if (alertas.isEmpty()) {
            return List.of();
        }

        // Extraemos IDs únicos
        List<Long> idsMercancias = alertas.stream()
                .map(AlertModel::getIdMercancia)
                .distinct()
                .toList();
        
        // Obtenemos mercancías en lote
        List<MercanciaResponse> mercancias;
        try {
            mercancias = mercanciaClient.obtenerMercanciasPorIds(idsMercancias);
        } catch (FeignException e) {
            throw new RemoteServiceException("Error al comunicarse con el microservicio de mercancias en lote");
        }

        // Creamos el mapa (Corregido: Collectors.toMap)
        Map<Long, MercanciaResponse> mapaMercancias = mercancias.stream()
                .collect(Collectors.toMap(MercanciaResponse::getId, m -> m));

        // CORRECCIÓN: Faltaba retornar la lista mapeando las alertas con el mapa
        return alertas.stream()
                .map(alerta -> mapToResponse(alerta, mapaMercancias.get(alerta.getIdMercancia())))
                .toList();
    }

    // Busca alerta por ID
    public AlertResponse obtenerPorId(@NonNull Long id) {

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

        AlertModel nuevaAlerta = new AlertModel();

        nuevaAlerta.setNombreAlerta(request.getNombreAlerta());
        nuevaAlerta.setMensajeAlerta(request.getMensajeAlerta());
        nuevaAlerta.setIdMercancia(request.getIdMercancia());

        AlertModel alertaGuardada = alertRepository.save(nuevaAlerta);

        return mapToResponse(alertaGuardada, mercancia);
    }

    // Actualiza alerta
    public AlertResponse actualizar(@NonNull Long id, AlertRequest request) {
        
        // CORRECCIÓN: Se quitó el punto y coma (;) después de findById
        AlertModel alertaExistente = alertRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe la alerta con id: " + id));
        
        MercanciaResponse mercancia = obtenerMercanciaDesdeServicio(request.getIdMercancia());

        alertaExistente.setNombreAlerta(request.getNombreAlerta());
        alertaExistente.setMensajeAlerta(request.getMensajeAlerta());
        alertaExistente.setIdMercancia(request.getIdMercancia());

        AlertModel alertaActualizada = alertRepository.save(alertaExistente);

        return mapToResponse(alertaActualizada, mercancia);
    }
            
    // Elimina alerta
    @SuppressWarnings("null")
public void eliminar(@NonNull Long id) {

        AlertModel alerta = alertRepository.findById(id)
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
