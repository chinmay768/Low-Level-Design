package model;

import enums.PackageStatus;

public class Parcel {

    private final String id;

    private final Order order;

    private final Dimensions dimensions;

    private PackageStatus status;

    public Parcel(String id,
                  Order order,
                  Dimensions dimensions) {

        this.id = id;
        this.order = order;
        this.dimensions = dimensions;
        this.status = PackageStatus.CREATED;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void updateStatus(PackageStatus status) {
        this.status = status;
    }
}