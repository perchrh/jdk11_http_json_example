package org.digitalsprouts.example.backend.api;

public class Customer {

    private final String customerNumber;

    public Customer(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
