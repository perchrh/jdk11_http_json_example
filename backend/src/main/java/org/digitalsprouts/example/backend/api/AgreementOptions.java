package org.digitalsprouts.example.backend.api;

public class AgreementOptions {

    private final boolean property;
    private final boolean otherProperty;
    private final double initialRatio;

    public AgreementOptions(boolean property, boolean otherProperty, double initialRatio) {
        this.property = property;
        this.otherProperty = otherProperty;
        this.initialRatio = initialRatio;
    }

    public boolean isProperty() {
        return property;
    }

    public boolean isOtherProperty() {
        return otherProperty;
    }

    public double getInitialRatio() {
        return initialRatio;
    }
}
