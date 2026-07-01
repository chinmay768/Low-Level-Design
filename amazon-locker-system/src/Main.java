import enums.CompartmentSize;
import enums.PackageStatus;
import model.*;
import model.Address;
import model.Dimensions;
import notification.NotificationProvider;
import notification.SmsNotificationProvider;
import repository.*;
import repository.impl.*;
import service.*;
import strategy.BestFitLockerStrategy;
import strategy.LockerAllocationStrategy;
import util.CompartmentSizeResolver;
import util.OTPGenerator;
import util.RandomOTPGenerator;

import java.time.Clock;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*----------------------------------------------------------
                        Repositories
        -----------------------------------------------------------*/

        LockerRepository lockerRepository = new InMemoryLockerRepository();
        ParcelRepository parcelRepository = new InMemoryParcelRepository();
        AccessTokenRepository tokenRepository = new InMemoryAccessTokenRepository();
        CompartmentRepository compartmentRepository = new InMemoryCompartmentRepository();

        /*----------------------------------------------------------
                        Utilities
        -----------------------------------------------------------*/

        OTPGenerator otpGenerator = new RandomOTPGenerator();
        Clock clock = Clock.systemDefaultZone();

        /*----------------------------------------------------------
                        Strategy
        -----------------------------------------------------------*/

        LockerAllocationStrategy allocationStrategy =
                new BestFitLockerStrategy(new CompartmentSizeResolver());

        /*----------------------------------------------------------
                        Services
        -----------------------------------------------------------*/

        LockerService lockerService =
                new LockerService(lockerRepository, allocationStrategy);

        TokenService tokenService =
                new TokenService(
                        otpGenerator,
                        clock,
                        tokenRepository
                );

        NotificationProvider notificationProvider =
                new SmsNotificationProvider();

        NotificationService notificationService =
                new NotificationService(notificationProvider);

        DeliveryService deliveryService =
                new DeliveryService(
                        lockerService,
                        tokenService,
                        notificationService,
                        parcelRepository,
                        compartmentRepository
                );

        PickupService pickupService =
                new PickupService(
                        lockerService,
                        tokenService,
                        parcelRepository,
                        compartmentRepository
                );

        /*----------------------------------------------------------
                        Create Locker Station
        -----------------------------------------------------------*/

        Locker locker = new Locker(
                "LOCKER-1",
                List.of(
                        new Compartment("S1", CompartmentSize.SMALL),
                        new Compartment("S2", CompartmentSize.SMALL),
                        new Compartment("M1", CompartmentSize.MEDIUM),
                        new Compartment("L1", CompartmentSize.LARGE),
                        new Compartment("XL1", CompartmentSize.EXTRA_LARGE)
                )
        );

        LockerStation station = new LockerStation(
                "STATION-1",
                new Address(
                        "MG Road",
                        "Bangalore",
                        "Karnataka",
                        "560001"
                ),
                List.of(locker)
        );

        lockerRepository.save(station);

        /*----------------------------------------------------------
                        Create Customer
        -----------------------------------------------------------*/

        Customer customer =
                new Customer(
                        "CUST-1",
                        "John Doe",
                        "9999999999"
                );

        /*----------------------------------------------------------
                        Create Order
        -----------------------------------------------------------*/

        Order order =
                new Order(
                        "ORDER-1",
                        customer
                );

        /*----------------------------------------------------------
                        Create Parcel
        -----------------------------------------------------------*/

        Parcel parcel =
                new Parcel(
                        "PARCEL-1",
                        order,
                        new Dimensions(
                                15,
                                10,
                                5
                        )
                );

        parcel.updateStatus(PackageStatus.IN_TRANSIT);

        /*----------------------------------------------------------
                        Deliver Parcel
        -----------------------------------------------------------*/

        System.out.println("\n=========== DELIVERY ===========");

        deliveryService.deliver(
                "STATION-1",
                parcel
        );

        System.out.println("Parcel Status : " + parcel.getStatus());

        /*----------------------------------------------------------
                        Customer Receives OTP
        -----------------------------------------------------------*/

        AccessToken token =
                tokenService.getToken(parcel.getId());

        System.out.println("Generated OTP : " + token.getOtp());

        /*----------------------------------------------------------
                        Pickup Parcel
        -----------------------------------------------------------*/

        System.out.println("\n=========== PICKUP ===========");

        pickupService.pickup(
                parcel.getId(),
                token.getOtp()
        );

        System.out.println("Parcel Status : " + parcel.getStatus());

        System.out.println("\n=========== SUCCESS ===========");

        System.out.println("Locker released successfully.");
        System.out.println("Parcel collected successfully.");
    }
}