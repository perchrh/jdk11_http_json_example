package org.digitalsprouts.example.backend.service;

import org.digitalsprouts.example.backend.api.Agreement;
import org.digitalsprouts.example.backend.api.Customer;

public class LetterService {

    public enum DeliveryStatus {OWL_DISPATCHED, PIGEON_DISPATCHED, DIGITAL_DELIVERY_QUEUED, PRINTED_LETTER_DELIVERY_QUEUED}

    public DeliveryStatus scheduleLetterDelivery(Customer customer, Agreement agreement) {
        return DeliveryStatus.DIGITAL_DELIVERY_QUEUED;
    }

}
