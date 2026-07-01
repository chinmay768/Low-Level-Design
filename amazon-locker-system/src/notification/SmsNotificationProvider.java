package notification;

import model.Customer;

public class SmsNotificationProvider
        implements NotificationProvider {

    @Override
    public void notify(Customer customer,
                       String message) {

        System.out.println(
                "SMS to "
                        + customer.getPhoneNumber()
                        + " : "
                        + message);
    }
}
