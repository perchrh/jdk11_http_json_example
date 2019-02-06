package org.digitalsprouts.example.http_client.service;

import org.digitalsprouts.example.backend.api.Agreement;
import org.digitalsprouts.example.backend.api.AgreementOptions;
import org.digitalsprouts.example.backend.api.Customer;
import org.digitalsprouts.example.backend.api.CustomerSpecification;
import org.digitalsprouts.example.backend.service.AgreementService;
import org.digitalsprouts.example.backend.service.CustomerService;
import org.digitalsprouts.example.backend.service.LetterService;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingRequest;
import org.digitalsprouts.example.http_client.api.CustomerOnboardingResponse;
import org.digitalsprouts.example.http_client.api.ProductSpecification;
import org.digitalsprouts.example.http_client.resource.MockedPersonLookupService;

import static org.digitalsprouts.example.backend.api.Agreement.AgreementStatus.*;

public class CustomerOnboardingServiceImpl implements CustomerOnboardingService {

    private final MockedPersonLookupService mockedPersonLookupService = new MockedPersonLookupService();

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
        // Orchestrate the calls to required services

        CustomerSpecification cc = mockedPersonLookupService.lookup(c.getNationalRegistrationId());
        Customer customer = customerService.createCustomer(cc, c.getEmailAddress(), c.getPhoneNumber());

        AgreementOptions options = asAgreementOptions(c.getProductSpecification());
        Agreement agreement = agreementService.createAgreement(customer, options);

        LetterService.DeliveryStatus status = letterService.scheduleLetterDelivery(customer, agreement);

        Agreement deliveredAgreement = agreementService.updateAgreementStatus(agreement, asAgreementStatus(status));

        CustomerOnboardingResponse response = new CustomerOnboardingResponse();
        response.setAgreementStatus(deliveredAgreement.getAgreementStatus());
        response.setAgreementNumber(agreement.getAgreementNumber());
        response.setCustomerNumber(customer.getCustomerNumber());

        return response;
    }

    private Agreement.AgreementStatus asAgreementStatus(LetterService.DeliveryStatus status) {
        return status.equals(LetterService.DeliveryStatus.DIGITAL_DELIVERY_QUEUED) ? DISPATCHED: AWAITING_DISPATCH;
    }

    private AgreementOptions asAgreementOptions(ProductSpecification productSpecification) {
        // dummy mapper from web facade to internal API objects
        return new AgreementOptions(productSpecification.getProductPropertyTwo(), true, productSpecification.getProductPropertyOne());
    }
}
