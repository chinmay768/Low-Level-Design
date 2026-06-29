package repository.impl;

import model.LockerStation;
import repository.LockerRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryLockerRepository
        implements LockerRepository {

    private final Map<String, LockerStation> lockers =
            new ConcurrentHashMap<>();

    @Override
    public LockerStation findById(String id) {
        return lockers.get(id);
    }

    @Override
    public void save(LockerStation station) {
        lockers.put(station.getId(), station);
    }
}
