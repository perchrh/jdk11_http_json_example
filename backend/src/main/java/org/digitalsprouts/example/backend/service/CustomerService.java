package org.digitalsprouts.example.backend.service;

import org.digitalsprouts.example.backend.api.Customer;
import org.digitalsprouts.example.backend.api.CustomerSpecification;

import java.util.Random;

public class CustomerService {

    Random dummyCustomerNumberGenerator = new Random(1337);

    public Customer createCustomer(CustomerSpecification c) {
        return new Customer(String.valueOf(dummyCustomerNumberGenerator.nextInt()));
    }


}
