package historial.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "historial")
public class HistorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHistorial;

    @Column(name = "id_usuario", nullable = false)
    private long idUsuario;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "hora_ingreso")
    private LocalTime horaIngreso;
    
    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;
    
    @Column(name = "punto_acceso", nullable = false)
    private String puntoAcceso;

    @Column(name = "estado_ingreso", nullable = false)
    private String estadoIngreso;



    
}
