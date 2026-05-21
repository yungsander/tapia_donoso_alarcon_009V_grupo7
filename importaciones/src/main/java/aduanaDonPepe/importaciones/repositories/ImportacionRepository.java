package aduanaDonPepe.importaciones.repositories;

import aduanaDonPepe.importaciones.models.ImportacionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportacionRepository extends JpaRepository<ImportacionModel, Long> {

}