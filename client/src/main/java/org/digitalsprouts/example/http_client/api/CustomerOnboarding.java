package org.digitalsprouts.example.http_client.api;

public class CustomerOnboarding {

    private String nationalRegistrationId;
    private String phoneNumber;
    private String emailAddress;
    private ProductSpecification productSpecification;

    public String getNationalRegistrationId() {
        return nationalRegistrationId;
    }

    public void setNationalRegistrationId(String nationalRegistrationId) {
        this.nationalRegistrationId = nationalRegistrationId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }
}
