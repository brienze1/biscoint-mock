package org.brienze.biscoint.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.model.Offer;

@Entity
@Table(name = "offers")
public class OfferEntity {

    @Id
    @NotEmpty
    @Column(name = "offer_id")
    private String offerId;

    @NotNull
    @Column(name = "base", columnDefinition = "DECIMAL(16,8)")
    private Quote base;

    @NotNull
    @Column(name = "quote", columnDefinition = "DECIMAL(16,8)")
    private Quote quote;

    @NotNull
    @Column(name = "operation")
    private Operation operation;

    @Column(name = "quoted_in_brl")
    private boolean quotedInBrl;

    @Positive
    @Column(name = "base_amount", columnDefinition = "DECIMAL(16,8)")
    private BigDecimal baseAmount;

    @Positive
    @Column(name = "unitary_value")
    private BigDecimal unitaryValue;

    @Positive
    @Column(name = "quote_amount", columnDefinition = "DECIMAL(16,8)")
    private BigDecimal quoteAmount;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @NotEmpty
    @Column(name = "api_key_id")
    private String apiKeyId;

    public OfferEntity(Offer offer) {
        setOfferId(offer.getOfferId());
        setBase(offer.getBase());
        setQuote(offer.getQuote());
        setOperation(offer.getOperation());
        setQuotedInBrl(offer.getQuotedInBrl());
        setBaseAmount(offer.getBaseAmount());
        setUnitaryValue(offer.getUnitaryValue());
        setQuoteAmount(offer.getQuoteAmount());
        setCreatedAt(offer.getCreatedAt());
        setExpiresAt(offer.getExpiresAt());
        setExpiresAt(offer.getExpiresAt());
        setConfirmedAt(offer.getConfirmedAt());
        setApiKeyId(offer.getApiKeyId());
    }

    public OfferEntity() {

    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public void setBase(Quote base) {
        this.base = base;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setQuotedInBrl(boolean quotedInBrl) {
        this.quotedInBrl = quotedInBrl;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public void setUnitaryValue(BigDecimal unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    public void setQuoteAmount(BigDecimal quoteAmount) {
        this.quoteAmount = quoteAmount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public void setApiKeyId(String apiKeyId) {
        this.apiKeyId = apiKeyId;
    }

    public String getOfferId() {
        return offerId;
    }

    public Quote getBase() {
        return base;
    }

    public Quote getQuote() {
        return quote;
    }

    public Operation getOperation() {
        return operation;
    }

    public boolean getQuotedInBrl() {
        return quotedInBrl;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public BigDecimal getUnitaryValue() {
        return unitaryValue;
    }

    public BigDecimal getQuoteAmount() {
        return quoteAmount;
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

    public Offer toOffer() {
        Offer offer = new Offer();

        offer.setOfferId(getOfferId());
        offer.setBase(getBase());
        offer.setOfferId(getOfferId());
        offer.setBase(getBase());
        offer.setQuote(getQuote());
        offer.setOperation(getOperation());
        offer.setQuotedInBrl(getQuotedInBrl());
        offer.setBaseAmount(getBaseAmount());
        offer.setUnitaryValue(getUnitaryValue());
        offer.setQuoteAmount(getQuoteAmount());
        offer.setCreatedAt(getCreatedAt());
        offer.setExpiresAt(getExpiresAt());
        offer.setConfirmedAt(getConfirmedAt());
        offer.setApiKeyId(getApiKeyId());

        return offer;
    }

}
