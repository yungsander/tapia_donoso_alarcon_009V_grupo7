package impuestos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import impuestos.clients.MercanciaClient;
import impuestos.dtos.request.ImpuestoRequest;
import impuestos.dtos.response.ImpuestoResponse;
import impuestos.dtos.response.MercanciaResponse;
import impuestos.exceptions.NotFoundException;
import impuestos.exceptions.RemoteServiceException;
import impuestos.models.ImpuestoModel;
import impuestos.repositories.ImpuestoRepository;

import feign.FeignException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImpuestoService {

    private final ImpuestoRepository impuestoRepository;
    private final MercanciaClient mercanciaClient;

    public ImpuestoService(
            ImpuestoRepository impuestoRepository,
            MercanciaClient mercanciaClient
    ) {
        this.impuestoRepository = impuestoRepository;
        this.mercanciaClient = mercanciaClient;
    }

    // Lista todos los impuestos
    public List<ImpuestoResponse> obtenerTodos() {

        return impuestoRepository.findAll()
                .stream()
                .map(this::mapToResponseConMercancia)
                .toList();
    }

    // Busca impuesto por ID
    public ImpuestoResponse obtenerPorId(Long id) {

        ImpuestoModel impuesto = impuestoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "No existe el impuesto con id: " + id));

        return mapToResponseConMercancia(impuesto);
    }

    // Registra nuevo impuesto
    public ImpuestoResponse guardar(ImpuestoRequest request) {

        // Verifica que la mercancía exista y obtiene su valor
        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(request.getIdMercancia());

        ImpuestoModel impuesto = new ImpuestoModel();

        impuesto.setTipoImpuesto(request.getTipoImpuesto());
        impuesto.setTasaImpuesto(request.getTasaImpuesto());
        impuesto.setMontoCalculado(calcularMonto(request.getTasaImpuesto(), mercancia.getValor()));
        impuesto.setFechaAplicacion(request.getFechaAplicacion());
        impuesto.setEstado(request.getEstado());
        impuesto.setIdMercancia(request.getIdMercancia());

        ImpuestoModel guardado =
                impuestoRepository.save(impuesto);

        return mapToResponse(guardado, mercancia);
    }

    // Actualiza impuesto existente
    public ImpuestoResponse actualizar(
            Long id,
            ImpuestoRequest request
    ) {

        ImpuestoModel impuesto =
                impuestoRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No existe el impuesto con id: " + id));

        // Verifica mercancía y recalcula el monto
        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(request.getIdMercancia());

        impuesto.setTipoImpuesto(request.getTipoImpuesto());
        impuesto.setTasaImpuesto(request.getTasaImpuesto());
        impuesto.setMontoCalculado(calcularMonto(request.getTasaImpuesto(), mercancia.getValor()));
        impuesto.setFechaAplicacion(request.getFechaAplicacion());
        impuesto.setEstado(request.getEstado());
        impuesto.setIdMercancia(request.getIdMercancia());

        ImpuestoModel actualizado =
                impuestoRepository.save(impuesto);

        return mapToResponse(actualizado, mercancia);
    }

    // Elimina impuesto
    public void eliminar(Long id) {

        ImpuestoModel impuesto =
                impuestoRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No existe el impuesto con id: " + id));

        impuestoRepository.delete(impuesto);
    }

    // Calcula el monto del impuesto sobre el valor de la mercancía
    private Double calcularMonto(Double tasa, Double valorMercancia) {
        return (tasa / 100.0) * valorMercancia;
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
    private ImpuestoResponse mapToResponseConMercancia(
            ImpuestoModel impuesto
    ) {

        MercanciaResponse mercancia =
                obtenerMercanciaDesdeServicio(
                        impuesto.getIdMercancia());

        return mapToResponse(impuesto, mercancia);
    }

    // DTO final
    private ImpuestoResponse mapToResponse(
            ImpuestoModel impuesto,
            MercanciaResponse mercancia
    ) {

        return ImpuestoResponse.builder()
                .id(impuesto.getId())
                .tipoImpuesto(impuesto.getTipoImpuesto())
                .tasaImpuesto(impuesto.getTasaImpuesto())
                .montoCalculado(impuesto.getMontoCalculado())
                .fechaAplicacion(impuesto.getFechaAplicacion())
                .estado(impuesto.getEstado())
                .idMercancia(impuesto.getIdMercancia())
                .mercancia(mercancia)
                .build();
    }
}
