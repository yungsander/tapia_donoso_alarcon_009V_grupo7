package mercancias.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Mercancia")
public class MercanciaModels {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String tipo;

    @Min(1)
    private Double peso;

    @Min(1)
    private Double valor;

    @NotBlank
    private String paisOrigen;

    private String estado;

}
