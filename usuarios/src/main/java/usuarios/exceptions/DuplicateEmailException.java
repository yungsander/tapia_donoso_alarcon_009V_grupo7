package usuarios.exceptions;

// Excepción para cuando se intenta registrar un email ya existente
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
