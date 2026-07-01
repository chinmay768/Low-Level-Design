package strategy;

import model.Compartment;
import model.LockerStation;
import model.Parcel;

import java.util.Optional;

public interface LockerAllocationStrategy {

    Optional<Compartment> allocate(
            Parcel parcel,
            LockerStation lockerStation);
}
