package exception;

public class InvalidOTPException extends AmazonLockerException {

    public InvalidOTPException() {
        super("Invalid OTP.");
    }

    public InvalidOTPException(String message) {
        super(message);
    }
}
