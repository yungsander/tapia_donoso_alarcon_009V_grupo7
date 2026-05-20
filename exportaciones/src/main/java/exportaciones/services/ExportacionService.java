package aduanaDonPepe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import aduanaDonPepe.clients.MercanciaClient;
import aduanaDonPepe.dtos.request.ExportacionRequest;
import aduanaDonPepe.dtos.response.ExportacionResponse;
import aduanaDonPepe.dtos.response.MercanciaResponse;
import aduanaDonPepe.exceptions.NotFoundException;
import aduanaDonPepe.exceptions.RemoteServiceException;
import aduanaDonPepe.models.ExportacionModel;
import aduanaDonPepe.repositories.ExportacionRepository;

import feign.FeignException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExportacionService {

    private final ExportacionRepository exportacionRepository;
    private final MercanciaClient mercanciaClient;

    public ExportacionService(
            ExportacionRepository exportacionRepository,
            MercanciaClient mercanciaClient
    ) {
        this.exportacionRepository = exportacionRepository;
        this.mercanciaClient = mercanciaClient;
    }

    // Lista todas las exportaciones
    public List<ExportacionResponse> obtenerTodas() {

        return exportacionRepository.findAll()
                .stream()
                .map(this::mapToResponseConMercancia)
                .toList();
    }

    // Busca exportación por ID
    public ExportacionResponse obtenerPorId(Long id) {

        ExportacionModel exportacion = exportacionRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "No existe la exportación con id: " + id));

        return mapToResponseConMercancia(exportacion);
    }

    // Guarda nueva exportación
    public ExportacionResponse guardar(ExportacionRequest request) {

        // Verifica que mercancía exista
        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(request.getIdMercancia());

        ExportacionModel exportacion = new ExportacionModel();

        exportacion.setFechaExportacion(request.getFechaExportacion());
        exportacion.setPaisDestino(request.getPaisDestino());
        exportacion.setEstado(request.getEstado());
        exportacion.setIdMercancia(request.getIdMercancia());
        exportacion.setPeso(request.getPeso());
        exportacion.setTipoMercancia(request.getTipoMercancia());

        ExportacionModel guardada =
                exportacionRepository.save(exportacion);

        return mapToResponse(guardada, mercancia);
    }

    // Actualiza exportación
    public ExportacionResponse actualizar(
            Long id,
            ExportacionRequest request
    ) {

        ExportacionModel exportacion =
                exportacionRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No existe la exportación con id: " + id));

        // Verifica mercancía
        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(request.getIdMercancia());

        exportacion.setFechaExportacion(request.getFechaExportacion());
        exportacion.setPaisDestino(request.getPaisDestino());
        exportacion.setEstado(request.getEstado());
        exportacion.setIdMercancia(request.getIdMercancia());
        exportacion.setPeso(request.getPeso());
        exportacion.setTipoMercancia(request.getTipoMercancia());

        ExportacionModel actualizada =
                exportacionRepository.save(exportacion);

        return mapToResponse(actualizada, mercancia);
    }

    // Elimina exportación
    public void eliminar(Long id) {

        ExportacionModel exportacion =
                exportacionRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No existe la exportación con id: " + id));

        exportacionRepository.delete(exportacion);
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
    private ExportacionResponse mapToResponseConMercancia(
            ExportacionModel exportacion
    ) {

        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(
                        exportacion.getIdMercancia());

        return mapToResponse(exportacion, mercancia);
    }

    // DTO final
    private ExportacionResponse mapToResponse(
            ExportacionModel exportacion,
            MercanciaResponse mercancia
    ) {

        return ExportacionResponse.builder()
                .id(exportacion.getId())
                .fechaExportacion(exportacion.getFechaExportacion())
                .paisDestino(exportacion.getPaisDestino())
                .estado(exportacion.getEstado())
                .idMercancia(exportacion.getIdMercancia())
                .peso(exportacion.getPeso())
                .tipoMercancia(exportacion.getTipoMercancia())
                .mercancia(mercancia)
                .build();
    }
}