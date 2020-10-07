package senin.exception;

public class IncorrectVertexRuntimeException extends RuntimeException {
    public IncorrectVertexRuntimeException() {
    }

    public IncorrectVertexRuntimeException(String message) {
        super(message);
    }

    public IncorrectVertexRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
