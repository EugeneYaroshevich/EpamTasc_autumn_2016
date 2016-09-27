package exception;

public class ControlEventException extends Exception {

    public ControlEventException() {
    }

    public ControlEventException(String message) {
        super(message);
    }

    public ControlEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControlEventException(Throwable cause) {
        super(cause);
    }

    public ControlEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
