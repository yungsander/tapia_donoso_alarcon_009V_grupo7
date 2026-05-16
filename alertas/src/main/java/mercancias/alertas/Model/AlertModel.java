package mercancias.alertas.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="alarma")
public class AlertModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID_Alerta;

    @Column(nullable = false, name="nombre_alarma")
    private String nombreAlarma;

    @Column(nullable = false, length = 500)
    private String mensajeAlarma;


}
