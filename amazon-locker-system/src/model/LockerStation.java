package model;

import java.util.List;

public class LockerStation {

    private final String id;

    private final Address address;

    private final List<Locker> lockers;

    public LockerStation(String id,
                         Address address,
                         List<Locker> lockers) {
        this.id = id;
        this.address = address;
        this.lockers = lockers;
    }

    public String getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public List<Locker> getLockers() {
        return lockers;
    }
}