package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.model.Offer;

public class OfferResponseDto {

    @JsonProperty("message")
    private final String message;

    @JsonProperty("data")
    private final OfferDto offer;

    public OfferResponseDto(Offer offer) {
        this.message = "";
        this.offer = new OfferDto(offer);
    }
}
