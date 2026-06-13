package mercancias.services;

import java.util.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import mercancias.models.MercanciaModels;
import mercancias.repositories.MercanciaRepository; 

@Service
@Transactional
public class MercanciaService {

    private final MercanciaRepository mercanciaRepository;

    public MercanciaService(MercanciaRepository mercanciaRepository) {
        this.mercanciaRepository = mercanciaRepository;
    }

    // LISTAR MERCANCIA
    public List<MercanciaModels> obtenerTodos() {
        return mercanciaRepository.findAll();
    }

    // REGISTRAR MERCANCIA
    public MercanciaModels guardar(MercanciaModels mercancia) {
        return mercanciaRepository.save(mercancia);
    }


    // ACTUALIZAR INFORMACION
    public MercanciaModels actualizar(Long id, MercanciaModels mercanciaActualizada) {
        Optional<MercanciaModels> mercanciaExistente =
                mercanciaRepository.findById(id);

        if (mercanciaExistente.isPresent()) {
            MercanciaModels mercancia =
            mercanciaExistente.get();
            mercancia.setNombre(mercanciaActualizada.getNombre());
            mercancia.setTipo(mercanciaActualizada.getTipo());
            mercancia.setPeso(mercanciaActualizada.getPeso());
            mercancia.setValor(mercanciaActualizada.getValor());
            mercancia.setPaisOrigen(mercanciaActualizada.getPaisOrigen());
            return mercanciaRepository.save(mercancia);
        }
    return null;
    }

    // ELIMINAR MERCANCIA
    public void eliminar(Long id) {
        mercanciaRepository.deleteById(id);
    }
}
