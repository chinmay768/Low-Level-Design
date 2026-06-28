package model;

import java.util.List;

public class Locker {

    private final String id;

    private final List<Compartment> compartments;

    public Locker(String id,
                  List<Compartment> compartments) {
        this.id = id;
        this.compartments = compartments;
    }

    public String getId() {
        return id;
    }

    public List<Compartment> getCompartments() {
        return compartments;
    }
}