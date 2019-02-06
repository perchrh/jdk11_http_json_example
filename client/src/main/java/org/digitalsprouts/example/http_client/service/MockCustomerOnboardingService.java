package org.digitalsprouts.example.http_client.service;

import org.digitalsprouts.example.http_client.api.CustomerOnboarding;
import org.digitalsprouts.example.http_client.api.ProductSpecification;

public class MockCustomerOnboardingService implements CustomerOnboardingService {

    @Override
    public CustomerOnboarding onboardNewCustomer() {
        CustomerOnboarding customerOnboarding = new CustomerOnboarding();
        customerOnboarding.setEmailAddress("user@host.com");
        customerOnboarding.setPhoneNumber("+155532771");
        customerOnboarding.setNationalRegistrationId("01020099417");

        ProductSpecification specification = new ProductSpecification();
        specification.setProductId("CAR_INSURANCE_YOUNG");
        specification.setProductPropertyOne(0.85d);
        specification.setProductPropertyTwo(false);
        specification.setProductPropertyThree(2);
        customerOnboarding.setProductSpecification(specification);

        return customerOnboarding;
    }
}
