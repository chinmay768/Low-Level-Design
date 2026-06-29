package service;

import enums.PackageStatus;
import model.Compartment;
import model.Parcel;
import repository.CompartmentRepository;
import repository.ParcelRepository;

public class PickupService {

    private final TokenService tokenService;

    private final ParcelRepository parcelRepository;

    private final CompartmentRepository compartmentRepository;

    public PickupService(
            LockerService lockerService, TokenService tokenService,
            ParcelRepository parcelRepository,
            CompartmentRepository compartmentRepository) {

        this.tokenService = tokenService;
        this.parcelRepository = parcelRepository;
        this.compartmentRepository = compartmentRepository;
    }

    public Parcel pickup(
            String parcelId,
            String otp) {

        tokenService.validateToken(
                parcelId,
                otp);

        Compartment compartment =
                compartmentRepository.findByParcelId(
                        parcelId);

        Parcel parcel =
                compartment.getParcel();

        parcel.updateStatus(
                PackageStatus.PICKED_UP);

        compartment.release();

        tokenService.consumeToken(parcelId);

        compartmentRepository.delete(parcelId);

        parcelRepository.save(parcel);

        return parcel;
    }
}
