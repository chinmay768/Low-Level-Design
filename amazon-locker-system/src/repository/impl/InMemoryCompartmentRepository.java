package repository.impl;

import model.Compartment;
import repository.CompartmentRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCompartmentRepository
        implements CompartmentRepository {

    private final Map<String, Compartment> map =
            new ConcurrentHashMap<>();

    @Override
    public void save(String parcelId,
                     Compartment compartment) {

        map.put(parcelId, compartment);
    }

    @Override
    public Compartment findByParcelId(
            String parcelId) {

        return map.get(parcelId);
    }

    @Override
    public void delete(String parcelId) {

        map.remove(parcelId);
    }
}