package documentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import documentos.model.DocumentosModel;

import java.util.*;

public interface DocumentosRepository extends JpaRepository<DocumentosModel, Long> {
    List<DocumentosModel> findByNumeroManifiesto(String numeroManifiesto);
    
}
