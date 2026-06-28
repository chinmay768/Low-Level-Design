package model;

import enums.CompartmentSize;
import enums.CompartmentStatus;

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

    public void reserve() {

        status = CompartmentStatus.RESERVED;
    }

    public void occupy(Parcel parcel) {

        this.parcel = parcel;

        status = CompartmentStatus.OCCUPIED;
    }

    public void release() {

        parcel = null;

        status = CompartmentStatus.AVAILABLE;
    }
}
