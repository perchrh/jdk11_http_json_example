package org.digitalsprouts.example.backend.service;

import org.digitalsprouts.example.backend.api.Customer;
import org.digitalsprouts.example.backend.api.CustomerSpecification;

import java.util.Random;

public class CustomerService {

    private final Random dummyCustomerNumberGenerator = new Random(1337);

    public Customer createCustomer(CustomerSpecification specification, String emailAddress, String phoneNumber) {
        // TODO forward all the fields
        return new Customer(String.valueOf(dummyCustomerNumberGenerator.nextInt()));
    }

}
