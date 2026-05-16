package mercancias.importaciones.services;

import java.util.List;

import org.springframework.stereotype.Service;

import feign.FeignException;
import jakarta.transaction.Transactional;

import mercancias.importaciones.clients.MercanciaClient;
import mercancias.importaciones.dtos.request.ImportacionRequest;
import mercancias.importaciones.dtos.response.ImportacionResponse;
import mercancias.importaciones.dtos.response.MercanciaResponse;
import mercancias.importaciones.exceptions.NotFoundException;
import mercancias.importaciones.exceptions.RemoteServiceException;
import mercancias.importaciones.models.ImportacionModel;
import mercancias.importaciones.repositories.ImportacionRepository;

@Service
@Transactional
public class ImportacionService {

    private final ImportacionRepository importacionRepository;
    private final MercanciaClient mercanciaClient;

    public ImportacionService(
            ImportacionRepository importacionRepository,
            MercanciaClient mercanciaClient
    ) {
        this.importacionRepository = importacionRepository;
        this.mercanciaClient = mercanciaClient;
    }

    // Lista todas las importaciones
    public List<ImportacionResponse> obtenerTodas() {

        return importacionRepository.findAll()
                .stream()
                .map(this::mapToResponseConMercancia)
                .toList();
    }

    // Busca importación por ID
    public ImportacionResponse obtenerPorId(Long id) {

        ImportacionModel importacion = importacionRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe la importación con id: " + id));

        return mapToResponseConMercancia(importacion);
    }

    // Guarda nueva importación
    public ImportacionResponse guardar(ImportacionRequest request) {

        // Verifica que la mercancía exista
        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(request.getIdMercancia());

        ImportacionModel importacion = new ImportacionModel();

        importacion.setFechaImportacion(request.getFechaImportacion());
        importacion.setPaisOrigen(request.getPaisOrigen());
        importacion.setEstado(request.getEstado());
        importacion.setIdMercancia(request.getIdMercancia());
        importacion.setPeso(request.getPeso());
        importacion.setTipoMercancia(request.getTipoMercancia());

        ImportacionModel guardada = importacionRepository.save(importacion);

        return mapToResponse(guardada, mercancia);
    }

    // Actualiza importación
    public ImportacionResponse actualizar(Long id, ImportacionRequest request) {

        ImportacionModel importacion = importacionRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe la importación con id: " + id));

        // Verifica mercancía
        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(request.getIdMercancia());

        importacion.setFechaImportacion(request.getFechaImportacion());
        importacion.setPaisOrigen(request.getPaisOrigen());
        importacion.setEstado(request.getEstado());
        importacion.setIdMercancia(request.getIdMercancia());
        importacion.setPeso(request.getPeso());
        importacion.setTipoMercancia(request.getTipoMercancia());

        ImportacionModel actualizada = importacionRepository.save(importacion);

        return mapToResponse(actualizada, mercancia);
    }

    // Elimina importación
    public void eliminar(Long id) {

        ImportacionModel importacion = importacionRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No existe la importación con id: " + id));

        importacionRepository.delete(importacion);
    }

    // Obtiene mercancía usando Feign
    private MercanciaResponse obtenerMercanciaDesdeServicio(Long idMercancia) {

        try {

            return mercanciaClient.obtenerMercanciaPorId(idMercancia);

        } catch (FeignException.NotFound e) {

            throw new NotFoundException(
                    "No existe la mercancía con id: " + idMercancia);

        } catch (FeignException e) {

            throw new RemoteServiceException(
                    "Error al comunicarse con el microservicio de mercancías");
        }
    }

    // Convierte entidad + mercancía
    private ImportacionResponse mapToResponseConMercancia(
            ImportacionModel importacion
    ) {

        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(importacion.getIdMercancia());

        return mapToResponse(importacion, mercancia);
    }

    // DTO final
    private ImportacionResponse mapToResponse(
            ImportacionModel importacion,
            MercanciaResponse mercancia
    ) {

        return ImportacionResponse.builder()
                .id(importacion.getId())
                .fechaImportacion(importacion.getFechaImportacion())
                .paisOrigen(importacion.getPaisOrigen())
                .estado(importacion.getEstado())
                .idMercancia(importacion.getIdMercancia())
                .peso(importacion.getPeso())
                .tipoMercancia(importacion.getTipoMercancia())
                .mercancia(mercancia)
                .build();
    }

}