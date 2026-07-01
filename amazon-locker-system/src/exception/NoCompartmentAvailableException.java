package exception;

public class NoCompartmentAvailableException extends AmazonLockerException {

    public NoCompartmentAvailableException() {
        super("No suitable compartment is available.");
    }

    public NoCompartmentAvailableException(String message) {
        super(message);
    }
}
