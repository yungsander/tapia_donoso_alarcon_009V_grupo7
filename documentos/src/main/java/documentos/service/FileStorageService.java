package documentos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    
    private final Path root = Paths.get("uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("no se pudo inicializar la carpeta de almacenamiento");
        }
    }

    public String guardarArchivo(MultipartFile archivo) {
        try {
            String nombreUnico = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Files.copy(archivo.getInputStream(), this.root.resolve(nombreUnico));

            return nombreUnico;
        } catch (Exception e) {
            throw new RuntimeException("no se pudo guardar el archivo. Error: " + e.getMessage());
        }
    }
}
