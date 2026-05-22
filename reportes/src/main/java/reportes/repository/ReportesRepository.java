package reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reportes.model.ReportesModel;
import java.util.List;

@Repository
public interface ReportesRepository extends JpaRepository<ReportesModel, Long> {
    
    List<ReportesModel> findByAnioTramiteAndMesTramite(Integer anio, Integer mes);
    List<ReportesModel> findByAnioTramite(Integer anio);
}
