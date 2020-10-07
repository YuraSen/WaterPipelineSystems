package senin.exception;

public class H2RuntimeException extends RuntimeException {
    public H2RuntimeException() {
    }

    public H2RuntimeException(String message) {
        super(message);
    }

    public H2RuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
