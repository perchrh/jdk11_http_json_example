package org.digitalsprouts.example.http_client.api;

import org.digitalsprouts.example.backend.api.Agreement.AgreementStatus;

public class CustomerOnboardingResponse {

    private String agreementNumber;
    private AgreementStatus agreementStatus;
    private String customerNumber;

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public AgreementStatus getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(AgreementStatus agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
