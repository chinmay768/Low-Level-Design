package exception;

public class AmazonLockerException extends RuntimeException {

    public AmazonLockerException(String message) {
        super(message);
    }

    public AmazonLockerException(String message, Throwable cause) {
        super(message, cause);
    }
}
