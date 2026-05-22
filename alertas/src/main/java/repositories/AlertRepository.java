package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.AlertModel;

@Repository
public interface AlertRepository extends JpaRepository<AlertModel, Long> {

}
