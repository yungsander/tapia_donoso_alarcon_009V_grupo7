package documentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import documentos.exception.NotFoundException;
import documentos.model.DocumentosModel;
import documentos.repository.DocumentosRepository;

@Service
public class DocumentosService {

    private final DocumentosRepository repository;
    private final FileStorageService fileStorageService;

    @Autowired
    public DocumentosService(DocumentosRepository repository, FileStorageService fileStorageService) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
    }

    public DocumentosModel guardarDocumento(MultipartFile archivo, String numeroManifiesto) {
        String nombreGuardado = fileStorageService.guardarArchivo(archivo);

        DocumentosModel nuevoDocumento = new DocumentosModel();
        nuevoDocumento.setNumeroManifiesto(numeroManifiesto);
        nuevoDocumento.setNombreOriginal(archivo.getOriginalFilename());
        nuevoDocumento.setTipoArchivo(archivo.getContentType());
        nuevoDocumento.setRutaAlmacenamiento(nombreGuardado);

        return repository.save(nuevoDocumento);
    }

    public List<DocumentosModel> obtenerPorManifiesto(String numeroManifiesto) {
        List<DocumentosModel> documentos = repository.findByNumeroManifiesto(numeroManifiesto);
        if (documentos.isEmpty()) {
            throw new NotFoundException("no se encontraron documentos para el manifiesto: " + numeroManifiesto);
        }
        return documentos;
    }
}
