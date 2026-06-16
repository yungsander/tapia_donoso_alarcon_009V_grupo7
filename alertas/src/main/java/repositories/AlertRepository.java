package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.AlertModel;

public interface AlertRepository extends JpaRepository<AlertModel, Long> {

}
