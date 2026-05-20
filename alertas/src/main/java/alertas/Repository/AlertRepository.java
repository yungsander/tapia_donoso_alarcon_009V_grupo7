package alertas.Repository;

import alertas.Model.AlertModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<AlertModel, Long> {

    List<AlertModel> findByIdAlert(Long idAlarma);
}
