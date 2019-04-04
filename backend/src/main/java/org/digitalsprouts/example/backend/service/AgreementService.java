package org.digitalsprouts.example.backend.service;

import org.digitalsprouts.example.backend.api.Agreement;
import org.digitalsprouts.example.backend.api.AgreementOptions;
import org.digitalsprouts.example.backend.api.Customer;

import java.util.Random;

public class AgreementService {

    private final static Random dummyAgreementNumberGenerator = new Random(0xdeadbeef);

    public Agreement createAgreement(Customer customer, AgreementOptions AgreementOptions) {
        return new Agreement(customer.getCustomerNumber(), AgreementOptions, String.valueOf(dummyAgreementNumberGenerator.nextInt()));
    }

    public Agreement updateAgreementStatus(Agreement agreement, Agreement.AgreementStatus agreementStatus) {
        agreement.setAgreementStatus(agreementStatus);
        return agreement;
    }

}
