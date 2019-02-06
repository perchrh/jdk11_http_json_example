package org.digitalsprouts.example.backend.api;

public class CustomerSpecification {
    private final String firstName;
    private final String lastName;
    private final String address; // simple example
    private final String nationalIdentificationNumber;

    public CustomerSpecification(String firstName, String lastName, String address, String nationalIdentificationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.nationalIdentificationNumber = nationalIdentificationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getNationalIdentificationNumber() {
        return nationalIdentificationNumber;
    }
}
