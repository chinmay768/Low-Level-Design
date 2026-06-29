package repository;

import model.Parcel;

public interface ParcelRepository {

    Parcel findById(String parcelId);

    void save(Parcel parcel);
}