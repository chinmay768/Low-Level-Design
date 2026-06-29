package repository;

import model.Compartment;

public interface CompartmentRepository {

    void save(String parcelId,
              Compartment compartment);

    Compartment findByParcelId(String parcelId);

    void delete(String parcelId);
}
