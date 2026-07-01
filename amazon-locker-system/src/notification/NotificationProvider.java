package notification;

import model.Customer;

public interface NotificationProvider {

    void notify(Customer customer,
                String message);
}