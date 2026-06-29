package repository;

import model.LockerStation;

public interface LockerRepository {

    LockerStation findById(String stationId);

    void save(LockerStation station);
}
