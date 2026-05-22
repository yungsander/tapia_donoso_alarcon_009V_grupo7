package impuestos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import impuestos.models.ImpuestoModel;

@Repository
public interface ImpuestoRepository extends JpaRepository<ImpuestoModel, Long> {

}
