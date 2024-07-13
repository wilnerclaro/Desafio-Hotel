package Desafio_Hotel.exception;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 3759575457881367859L;

    public ValidationException(String menssagem) {
        super(menssagem);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
