package documentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import documentos.model.DocumentosModel;

import java.util.*;

@Repository
public interface DocumentosRepository extends JpaRepository<DocumentosModel, Long> {
    List<DocumentosModel> findByNumeroManifiesto(String numeroManifiesto);
    
}
