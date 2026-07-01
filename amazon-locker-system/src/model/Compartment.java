package model;

import enums.CompartmentSize;
import enums.CompartmentStatus;

import static enums.CompartmentStatus.OCCUPIED;

public class Compartment {

    private final String id;

    private final CompartmentSize size;

    private CompartmentStatus status;

    private Parcel parcel;

    public Compartment(String id,
                       CompartmentSize size) {

        this.id = id;
        this.size = size;
        this.status = CompartmentStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public CompartmentSize getSize() {
        return size;
    }

    public CompartmentStatus getStatus() {
        return status;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public synchronized boolean reserve() {

        if (status != CompartmentStatus.AVAILABLE)
            return false;

        status = CompartmentStatus.RESERVED;

        return true;
    }

    public synchronized void occupy(Parcel parcel) {
        if(status != CompartmentStatus.RESERVED)
            throw new IllegalStateException(
                    "Compartment must be reserved first.");

        this.parcel = parcel;

        status = OCCUPIED;
    }

    public synchronized void release() {

        if(status != OCCUPIED)
            throw new IllegalStateException();

        parcel = null;
        status = CompartmentStatus.AVAILABLE;
    }
}
