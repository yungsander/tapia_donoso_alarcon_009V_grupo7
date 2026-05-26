package historial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import historial.model.HistorialModel;

@Repository
public interface HistorialRepository extends JpaRepository<HistorialModel, Long> {

    List<HistorialModel> findByIdUsuario(Long IdUsuario);

    List<HistorialModel> findByVigente(boolean Vigente);
    
}
