package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.model.Offer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDto {

    @JsonProperty("offerId")
    private final String offerId;

    @JsonProperty("base")
    private final String base;

    @JsonProperty("quote")
    private final String quote;

    @JsonProperty("op")
    private final String operation;

    @JsonProperty("isQuote")
    private final Boolean quotedInBrl;

    @JsonProperty("baseAmount")
    private final BigDecimal baseAmount;

    @JsonProperty("quoteAmount")
    private final BigDecimal quoteAmount;

    @JsonProperty("efPrice")
    private final BigDecimal unitaryValue;

    @JsonProperty("createdAt")
    private final LocalDateTime createdAt;

    @JsonProperty("expiresAt")
    private final LocalDateTime expiresAt;

    @JsonProperty("confirmedAt")
    private final LocalDateTime confirmedAt;

    @JsonProperty("apiKeyId")
    private final String apiKeyId;

    public OfferDto(Offer offer) {
        this.offerId = offer.getOfferId();
        this.base = offer.getBase().name().toUpperCase();
        this.quote = offer.getQuote().name().toUpperCase();
        this.operation = offer.getOperation().getName().toLowerCase();
        this.quotedInBrl = offer.getQuotedInBrl();
        this.baseAmount = offer.getBaseAmount().setScale(8, RoundingMode.HALF_UP);
        this.unitaryValue = offer.getUnitaryValue().setScale(2, RoundingMode.HALF_UP);
        this.quoteAmount = offer.getQuoteAmount().setScale(2, RoundingMode.HALF_UP);
        this.createdAt = offer.getCreatedAt();
        this.expiresAt = offer.getExpiresAt();
        this.confirmedAt = offer.getConfirmedAt();
        this.apiKeyId = offer.getApiKeyId();
    }
}
