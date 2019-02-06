package org.digitalsprouts.example.http_client.service;

import org.digitalsprouts.example.backend.api.Agreement.AgreementStatus;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingRequest;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingResponse;

public class MockCustomerOnboardingService implements CustomerOnboardingService {

    @Override
    public CustomerOnboardingResponse onboardNewCustomer(CustomerOnboardingRequest ignored) {
        CustomerOnboardingResponse response = new CustomerOnboardingResponse();
        response.setAgreementNumber("123");
        response.setAgreementStatus(AgreementStatus.DISPATCHED);
        return response;
    }

}
