package exportaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import exportaciones.models.ExportacionModel;

public interface ExportacionRepository extends JpaRepository<ExportacionModel, Long> {

}