package org.digitalsprouts.example.http_client.resource;

import org.digitalsprouts.example.backend.api.CustomerSpecification;

public class MockedPersonLookupService {

    public CustomerSpecification lookup(String nationalRegistrationNumber) {
        // FIXME mocked implementation

        return new CustomerSpecification("Johnny", "Bravo", "Long Drive 13123", nationalRegistrationNumber);
    }
}
