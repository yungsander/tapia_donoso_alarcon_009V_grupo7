package historial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import historial.model.HistorialModel;

public interface HistorialRepository extends JpaRepository<HistorialModel, Long> {

    List<HistorialModel> findByIdUsuario(Long IdUsuario);

    List<HistorialModel> findByVigente(boolean Vigente);
    
}
