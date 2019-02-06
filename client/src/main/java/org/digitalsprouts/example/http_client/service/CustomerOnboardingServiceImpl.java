package org.digitalsprouts.example.http_client.service;

import org.digitalsprouts.example.backend.service.AgreementService;
import org.digitalsprouts.example.backend.service.CustomerService;
import org.digitalsprouts.example.backend.service.LetterService;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingRequest;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingResponse;

public class CustomerOnboardingServiceImpl implements CustomerOnboardingService {

    private AgreementService agreementService;

    private LetterService letterService;

    private CustomerService customerService;

    public CustomerOnboardingServiceImpl(AgreementService agreementService, CustomerService customerService, LetterService letterService) {
        this.agreementService = agreementService;
        this.customerService = customerService;
        this.letterService = letterService;
    }

    @Override
    public CustomerOnboardingResponse onboardNewCustomer(CustomerOnboardingRequest c) {
        return null;
    }
}
