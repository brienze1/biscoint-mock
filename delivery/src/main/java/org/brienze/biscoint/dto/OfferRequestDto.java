package org.brienze.biscoint.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.model.OfferRequest;
import org.brienze.biscoint.validator.Validators;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfferRequestDto {

    @JsonProperty("op")
    private String operation;

    @JsonProperty("amount")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;

    @JsonProperty("isQuote")
    private boolean quotedOnBrl;

    public void setOperation(String operation) {
        Validators.validateNotEmpty(operation, "Invalid operation.");

        this.operation = operation;
    }

    public void setAmount(BigDecimal amount) {
        Validators.validateGreaterThanZero(amount, "Amount must be greater than zero.");

        this.amount = amount;
    }

    public void setQuotedOnBrl(boolean quotedOnBrl) {
        this.quotedOnBrl = quotedOnBrl;
    }

    public OfferRequest toOffer() {
        OfferRequest offer = new OfferRequest();

        offer.setOperation(Operation.get(operation));
        offer.setAmount(amount);
        offer.setQuotedOnBrl(quotedOnBrl);

        return offer;
    }

}
