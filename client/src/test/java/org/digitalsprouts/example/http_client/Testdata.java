package org.digitalsprouts.example.http_client;

import org.digitalsprouts.example.http_client.api.CustomerOnboardingRequest;
import org.digitalsprouts.example.http_client.api.ProductSpecification;

public final class Testdata {


    static CustomerOnboardingRequest createDummyRequest() {
        CustomerOnboardingRequest customerOnboardingRequest = new CustomerOnboardingRequest();
        customerOnboardingRequest.setEmailAddress("user@host.com");
        customerOnboardingRequest.setPhoneNumber("+155532771");
        customerOnboardingRequest.setNationalRegistrationId("01020099417");

        ProductSpecification specification = new ProductSpecification();
        specification.setProductId("CAR_INSURANCE_YOUNG");
        specification.setProductPropertyOne(0.85d);
        specification.setProductPropertyTwo(false);
        specification.setProductPropertyThree(2);
        customerOnboardingRequest.setProductSpecification(specification);
        return customerOnboardingRequest;
    }
}
