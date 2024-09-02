package es.cic.curso.coleccionCartas.exception;

public class ActualizarException extends RuntimeException {

    public ActualizarException(String message) {
        super(message);
    }

    public ActualizarException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActualizarException(Throwable cause) {
        super(cause);
    }

    public ActualizarException(String message, Long id) {
        super(String.format("%s (ID: %d)", message, id));
    }
}
