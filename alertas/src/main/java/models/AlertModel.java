package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alarma")
public class AlertModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la alerta
    @Column(name = "nombre_alarma", nullable = false)
    private String nombreAlerta;

    // Mensaje de la alerta
    @Column(name = "mensaje_alarma", nullable = false, length = 500)
    private String mensajeAlerta;

    // ID de la mercancía asociada
    @Column(name = "id_mercancia", nullable = false)
    private Long idMercancia;

}
