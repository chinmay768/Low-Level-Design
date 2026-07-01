package exception;

public class LockerNotFoundException extends AmazonLockerException {

    public LockerNotFoundException(String lockerId) {
        super("Locker station not found: " + lockerId);
    }
}