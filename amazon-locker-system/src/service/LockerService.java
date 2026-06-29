package service;

import exception.NoCompartmentAvailableException;
import model.Compartment;
import model.LockerStation;
import model.Parcel;
import repository.LockerRepository;
import strategy.LockerAllocationStrategy;

public class LockerService {

    private final LockerRepository lockerRepository;
    private final LockerAllocationStrategy allocationStrategy;

    public LockerService(
            LockerRepository lockerRepository,
            LockerAllocationStrategy allocationStrategy) {

        this.lockerRepository = lockerRepository;
        this.allocationStrategy = allocationStrategy;
    }

    public Compartment allocateLocker(
            String stationId,
            Parcel parcel) {

        LockerStation station =
                lockerRepository.findById(stationId);

        return allocationStrategy
                .allocate(parcel, station)
                .orElseThrow(() -> new NoCompartmentAvailableException(
                                "No compartment available"));
    }
}
