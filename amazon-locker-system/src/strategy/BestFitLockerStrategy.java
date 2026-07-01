package strategy;

import enums.CompartmentSize;
import enums.CompartmentStatus;
import model.Compartment;
import model.Locker;
import model.LockerStation;
import model.Parcel;
import util.CompartmentSizeResolver;

import java.util.Optional;

public class BestFitLockerStrategy
        implements LockerAllocationStrategy {

    private final CompartmentSizeResolver resolver;

    public BestFitLockerStrategy(
            CompartmentSizeResolver resolver) {

        this.resolver = resolver;
    }

    @Override
    public Optional<Compartment> allocate(
            Parcel parcel,
            LockerStation station) {

        CompartmentSize required =
                resolver.resolve(parcel.getDimensions());

        for (CompartmentSize current : CompartmentSize.values()) {

            if (current.ordinal() < required.ordinal())
                continue;

            for (Locker locker : station.getLockers()) {

                for (Compartment compartment :
                        locker.getCompartments()) {

                    if (compartment.getSize() != current)
                        continue;

                    if (compartment.reserve()) {
                        return Optional.of(compartment);
                    }
                }
            }
        }

        return Optional.empty();
    }
}
