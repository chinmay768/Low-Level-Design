package repository.impl;

import model.Parcel;
import repository.ParcelRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryParcelRepository
        implements ParcelRepository {

    private final Map<String, Parcel> parcels =
            new ConcurrentHashMap<>();

    @Override
    public Parcel findById(String id) {
        return parcels.get(id);
    }

    @Override
    public void save(Parcel parcel) {
        parcels.put(parcel.getId(), parcel);
    }
}
