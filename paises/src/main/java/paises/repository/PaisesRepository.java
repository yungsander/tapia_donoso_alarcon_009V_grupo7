package paises.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import paises.model.PaisesModel;

@Repository
public interface PaisesRepository extends JpaRepository<PaisesModel, Long> {
    Optional<PaisesModel> findByCodigoIso(String codigoIso);

    
}
