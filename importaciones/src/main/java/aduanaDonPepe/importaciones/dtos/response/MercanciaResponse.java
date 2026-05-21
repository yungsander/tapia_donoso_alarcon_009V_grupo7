package aduanaDonPepe.importaciones.dtos.response;

import lombok.Data;

@Data
public class MercanciaResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
}