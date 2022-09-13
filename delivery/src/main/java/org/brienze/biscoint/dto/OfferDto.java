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
    private String offerId;

    @JsonProperty("base")
    private String base;

    @JsonProperty("quote")
    private String quote;

    @JsonProperty("op")
    private String operation;

    @JsonProperty("isQuote")
    private Boolean quotedInBrl;

    @JsonProperty("baseAmount")
    private BigDecimal baseAmount;

    @JsonProperty("quoteAmount")
    private BigDecimal quoteAmount;

    @JsonProperty("efPrice")
    private BigDecimal unitaryValue;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("expiresAt")
    private LocalDateTime expiresAt;

    @JsonProperty("confirmedAt")
    private LocalDateTime confirmedAt;

    @JsonProperty("apiKeyId")
    private String apiKeyId;

    public OfferDto(Offer offer) {
        this.offerId = offer.getOfferId();
        this.base = offer.getBase().name().toUpperCase();
        this.quote = offer.getQuote().name().toUpperCase();
        this.operation = offer.getOperation().getName().toLowerCase();
        this.quotedInBrl = offer.getQuotedInBrl();
        this.unitaryValue = offer.getUnitaryValue().setScale(2, RoundingMode.HALF_UP);
        this.createdAt = offer.getCreatedAt();
        this.expiresAt = offer.getExpiresAt();
        this.confirmedAt = offer.getConfirmedAt();
        this.apiKeyId = offer.getApiKeyId();

        if (this.quotedInBrl) {
            this.quoteAmount = offer.getQuoteAmount().setScale(8, RoundingMode.HALF_UP);
            this.baseAmount = offer.getBaseAmount().setScale(2, RoundingMode.HALF_UP);
        } else {
            this.quoteAmount = offer.getQuoteAmount().setScale(2, RoundingMode.HALF_UP);
            this.baseAmount = offer.getBaseAmount().setScale(8, RoundingMode.HALF_UP);
        }
    }

    public OfferDto() {
    }

    public String getOfferId() {
        return offerId;
    }

    public String getBase() {
        return base;
    }

    public String getQuote() {
        return quote;
    }

    public String getOperation() {
        return operation;
    }

    public Boolean getQuotedInBrl() {
        return quotedInBrl;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public BigDecimal getQuoteAmount() {
        return quoteAmount;
    }

    public BigDecimal getUnitaryValue() {
        return unitaryValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public String getApiKeyId() {
        return apiKeyId;
    }
}
