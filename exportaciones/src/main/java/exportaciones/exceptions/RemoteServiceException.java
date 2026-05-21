package exportaciones.exceptions;
// Excepción para errores al consumir otro microservicio
public class RemoteServiceException extends RuntimeException {
    public RemoteServiceException(String message) {
        super(message);
    }
}