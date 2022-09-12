package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.model.Offer;

public class OfferResponseDto {

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private OfferDto offer;

    public OfferResponseDto(Offer offer) {
        this.message = "";
        this.offer = new OfferDto(offer);
    }

    public OfferResponseDto() {
    }

    public String getMessage() {
        return message;
    }

    public OfferDto getOffer() {
        return offer;
    }
}
