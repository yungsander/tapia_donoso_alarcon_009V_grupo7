package importaciones.repositories;

import importaciones.models.ImportacionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportacionRepository extends JpaRepository<ImportacionModel, Long> {

}