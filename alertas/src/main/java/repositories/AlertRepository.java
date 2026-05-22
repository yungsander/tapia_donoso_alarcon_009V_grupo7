package alertas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alertas.models.AlertModel;

@Repository
public interface AlertRepository extends JpaRepository<AlertModel, Long> {

}
