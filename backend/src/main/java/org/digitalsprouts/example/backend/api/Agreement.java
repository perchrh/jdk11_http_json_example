package org.digitalsprouts.example.backend.api;

public class Agreement {

    public enum AgreementStatus { AWAITING_DISPATCH, DISPATCHED, ACTIVE, CANCELLED}

    private final String customerId;
    private final AgreementOptions agreementOptions;
    private final String agreementNumber;

    public Agreement(String customerId, AgreementOptions agreementOptions, String agreementNumber) {
        this.customerId = customerId;
        this.agreementOptions = agreementOptions;
        this.agreementNumber = agreementNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public AgreementOptions getAgreementOptions() {
        return agreementOptions;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }
}
