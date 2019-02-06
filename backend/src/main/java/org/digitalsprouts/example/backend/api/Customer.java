package org.digitalsprouts.example.backend.api;

public class Customer {

    private final String customerId;

    public Customer(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
}
