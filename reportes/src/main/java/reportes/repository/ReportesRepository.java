package reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reportes.model.ReportesModel;
import java.util.List;

public interface ReportesRepository extends JpaRepository<ReportesModel, Long> {
    
    List<ReportesModel> findByAnioTramiteAndMesTramite(Integer anio, Integer mes);
    List<ReportesModel> findByAnioTramite(Integer anio);
}
