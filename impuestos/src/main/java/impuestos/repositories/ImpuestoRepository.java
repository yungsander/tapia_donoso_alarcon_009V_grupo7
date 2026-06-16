package impuestos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import impuestos.models.ImpuestoModel;

public interface ImpuestoRepository extends JpaRepository<ImpuestoModel, Long> {

}
