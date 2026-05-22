package documentos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "documentos")
public class DocumentosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;

    private String numeroManifiesto;
    private String nombreOriginal;
    private String tipoArchivo;
    private String rutaAlmacenamiento;
    private LocalDateTime fechaSubida = LocalDateTime.now();
    
}
