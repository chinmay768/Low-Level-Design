package exception;

public class OTPExpiredException extends AmazonLockerException {

    public OTPExpiredException() {
        super("OTP has expired.");
    }

    public OTPExpiredException(String message) {
        super(message);
    }
}