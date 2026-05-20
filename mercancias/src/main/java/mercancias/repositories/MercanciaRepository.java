package aduanaDonPepe.mercancias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import aduanaDonPepe.mercancias.models.MercanciaModels;

public interface MercanciaRepository extends JpaRepository<MercanciaModels, Long> {
}
