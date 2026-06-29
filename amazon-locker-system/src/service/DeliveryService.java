package service;

import enums.PackageStatus;
import model.AccessToken;
import model.Compartment;
import model.Parcel;
import repository.CompartmentRepository;
import repository.ParcelRepository;

public class DeliveryService {

    private final LockerService lockerService;

    private final TokenService tokenService;

    private final NotificationService notificationService;

    private final ParcelRepository parcelRepository;

    private final CompartmentRepository compartmentRepository;

    public DeliveryService(
            LockerService lockerService,
            TokenService tokenService,
            NotificationService notificationService,
            ParcelRepository parcelRepository, CompartmentRepository compartmentRepository) {

        this.lockerService = lockerService;
        this.tokenService = tokenService;
        this.notificationService = notificationService;
        this.parcelRepository = parcelRepository;
        this.compartmentRepository = compartmentRepository;
    }

    public void deliver(String stationId,
                        Parcel parcel) {

        if(parcel.getStatus()!=PackageStatus.IN_TRANSIT){

        }

        Compartment compartment =
                lockerService.allocateLocker(
                        stationId,
                        parcel);

        compartment.occupy(parcel);

        compartmentRepository.save(
                parcel.getId(),
                compartment);

        parcel.updateStatus(
                PackageStatus.DELIVERED_TO_LOCKER);

        parcelRepository.save(parcel);

        AccessToken token =
                tokenService.createToken(parcel);

        notificationService.notifyCustomer(
                parcel.getOrder()
                        .getCustomer(),
                "OTP : "
                        + token.getOtp());
    }
}
