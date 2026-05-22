package usuarios.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo del usuario
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    // Apellido del usuario
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    // Correo electrónico único
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    // Número de teléfono
    @Column(name = "telefono", length = 20)
    private String telefono;

    // Fecha de nacimiento
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    // Estado del usuario: ACTIVO, INACTIVO, SUSPENDIDO
    @Column(nullable = false, length = 50)
    private String estado;

}
