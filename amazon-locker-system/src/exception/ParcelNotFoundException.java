package exception;

public class ParcelNotFoundException extends AmazonLockerException {

    public ParcelNotFoundException(String parcelId) {
        super("Parcel not found: " + parcelId);
    }
}