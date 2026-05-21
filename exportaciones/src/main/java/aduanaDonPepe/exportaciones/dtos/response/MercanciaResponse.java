package aduanaDonPepe.exportaciones.dtos.response;

import lombok.Data;

@Data
public class MercanciaResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Double valor;
    private Double peso;
}