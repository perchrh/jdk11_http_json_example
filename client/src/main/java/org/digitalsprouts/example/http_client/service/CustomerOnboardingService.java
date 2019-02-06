package org.digitalsprouts.example.http_client.service;

import org.digitalsprouts.example.http_client.api.CustomerOnboardingRequest;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingResponse;

public interface CustomerOnboardingService {
    CustomerOnboardingResponse onboardNewCustomer(CustomerOnboardingRequest customerOnboardingRequest);
}
