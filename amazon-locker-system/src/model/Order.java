package model;

public class Order {

    private final String id;

    private final Customer customer;

    public Order(String id,
                 Customer customer) {

        this.id = id;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }
}