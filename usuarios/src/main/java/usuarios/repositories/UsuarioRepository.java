package usuarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import usuarios.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    // Verifica si ya existe un usuario con ese email
    boolean existsByEmail(String email);

}
