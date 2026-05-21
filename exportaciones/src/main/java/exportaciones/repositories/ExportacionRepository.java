package exportaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exportaciones.models.ExportacionModel;

@Repository
public interface ExportacionRepository extends JpaRepository<ExportacionModel, Long> {

}