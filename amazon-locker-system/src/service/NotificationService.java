package service;

import model.Customer;
import notification.NotificationProvider;

public class NotificationService {

    private final NotificationProvider provider;

    public NotificationService(
            NotificationProvider provider) {

        this.provider = provider;
    }

    public void notifyCustomer(
            Customer customer,
            String message) {

        provider.notify(customer,
                message);
    }
}
