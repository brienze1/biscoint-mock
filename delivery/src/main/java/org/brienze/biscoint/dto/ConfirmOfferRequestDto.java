package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmOfferRequestDto {

    @JsonProperty("offerId")
    private String offerId;

    public String getOfferId() {
        return offerId;
    }
}
