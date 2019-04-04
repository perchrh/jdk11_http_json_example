package org.digitalsprouts.example.backend.api;

public class Agreement {

    private final String customerNumber;
    private final AgreementOptions agreementOptions;
    private final String agreementNumber;
    private AgreementStatus agreementStatus = AgreementStatus.CREATED;
    public Agreement(String customerNumber, AgreementOptions agreementOptions, String agreementNumber) {
        this.customerNumber = customerNumber;
        this.agreementOptions = agreementOptions;
        this.agreementNumber = agreementNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public AgreementOptions getAgreementOptions() {
        return agreementOptions;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public AgreementStatus getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(AgreementStatus agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public enum AgreementStatus {CREATED, AWAITING_DISPATCH, DISPATCHED, ACTIVE, CANCELLED}
}
