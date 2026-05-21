package importaciones.repositories;

import importaciones.models.ImportacionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportacionRepository extends JpaRepository<ImportacionModel, Long> {

}