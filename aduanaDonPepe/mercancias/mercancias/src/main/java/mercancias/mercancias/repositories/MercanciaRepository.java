package mercancias.mercancias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mercancias.mercancias.models.MercanciaModels;

public interface MercanciaRepository extends JpaRepository<MercanciaModels, Long> {
}
