package usuarios.exceptions;

// Excepción para recursos que no existen
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
