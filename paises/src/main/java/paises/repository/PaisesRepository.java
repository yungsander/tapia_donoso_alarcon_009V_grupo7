package paises.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import paises.model.PaisesModel;

public interface PaisesRepository extends JpaRepository<PaisesModel, Long> {
    Optional<PaisesModel> findByCodigoIso(String codigoIso);

    
}
