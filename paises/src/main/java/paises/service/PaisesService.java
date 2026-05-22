package paises.service;

import java.util.List;

import org.springframework.stereotype.Service;

import paises.dto.response.PaisesResponseDTO;
import paises.exception.NotFoundException;
import paises.model.PaisesModel;
import paises.repository.PaisesRepository;

@Service
public class PaisesService {

    private final PaisesRepository repository;

    public PaisesService(PaisesRepository repository) {
        this.repository = repository;
    }
    
    public List<PaisesModel> listarTodos() {
        return repository.findAll();
    }

    public PaisesResponseDTO ObtenerPorId(Long id) {
        PaisesModel model = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("no se encontro el pais con id: " + id));
        
        return new PaisesResponseDTO(model.getId(), model.getCodigoIso(), model.getNombre());
    }
}
