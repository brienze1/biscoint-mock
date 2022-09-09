package org.brienze.biscoint.model;

import org.brienze.biscoint.validator.Validators;

import java.math.BigDecimal;

import static org.brienze.biscoint.enums.Operation.BUY;
import static org.brienze.biscoint.enums.Operation.SELL;

public class Client {

    private String apiKey;
    private String name;
    private BigDecimal bitcoinBalance;
    private BigDecimal brlBalance;

    public void setApiKey(String apiKey) {
        Validators.validateNull(this.apiKey, "apiKey already exists.");
        Validators.validateNotEmpty(apiKey, "apiKey cannot be empty.");
        this.apiKey = apiKey;
    }

    public void setName(String name) {
        Validators.validateNotEmpty(name, "name cannot be empty.");
        this.name = name;
    }

    public void setBitcoinBalance(BigDecimal bitcoinBalance) {
        Validators.validatePositive(bitcoinBalance, "bitcoinBalance cannot be less than zero.");
        this.bitcoinBalance = bitcoinBalance;
    }

    public void setBrlBalance(BigDecimal brlBalance) {
        Validators.validatePositive(brlBalance, "brlBalance cannot be less than zero.");
        this.brlBalance = brlBalance;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBitcoinBalance() {
        return bitcoinBalance;
    }

    public BigDecimal getBrlBalance() {
        return brlBalance;
    }

    public void hasBalanceForTransaction(Offer offer) {
        Validators.validateNotNull(offer, "offer cannot be null.");

        if (!offer.getQuotedInBrl() && offer.getOperation().equals(BUY)) {
            Validators.validateGreaterThanZero(offer.getQuoteAmount(), "transaction value cannot be less than zero.");
            Validators.validateGreaterThanZero(this.brlBalance, "brlBalance cannot be less than zero.");
            Validators.validatePositive(this.brlBalance.compareTo(offer.getQuoteAmount()), "Bitcoin balance cannot be less than transaction value.");
        } else if (!offer.getQuotedInBrl() && offer.getOperation().equals(SELL)) {
            Validators.validateGreaterThanZero(offer.getQuoteAmount(), "transaction value cannot be less than zero.");
            Validators.validateGreaterThanZero(this.bitcoinBalance, "bitcoinBalance cannot be less than zero.");
            Validators.validatePositive(this.bitcoinBalance.compareTo(offer.getBaseAmount()), "Brl balance cannot be less than transaction value.");
        } else if (offer.getQuotedInBrl() && offer.getOperation().equals(BUY)) {
            Validators.validateGreaterThanZero(offer.getQuoteAmount(), "transaction value cannot be less than zero.");
            Validators.validateGreaterThanZero(this.bitcoinBalance, "bitcoinBalance cannot be less than zero.");
            Validators.validatePositive(this.bitcoinBalance.compareTo(offer.getQuoteAmount()), "Brl balance cannot be less than transaction value.");
        } else if (offer.getQuotedInBrl() && offer.getOperation().equals(SELL)) {
            Validators.validateGreaterThanZero(offer.getQuoteAmount(), "transaction value cannot be less than zero.");
            Validators.validateGreaterThanZero(this.brlBalance, "brlBalance cannot be less than zero.");
            Validators.validatePositive(this.brlBalance.compareTo(offer.getBaseAmount()), "Bitcoin balance cannot be less than transaction value.");
        }
    }

    public void removeBalance(Offer offer) {
        hasBalanceForTransaction(offer);

        if (!offer.getQuotedInBrl() && offer.getOperation().equals(BUY)) {
            this.brlBalance = this.brlBalance.subtract(offer.getQuoteAmount());
            this.bitcoinBalance = this.bitcoinBalance.add(offer.getBaseAmount());
        } else if (!offer.getQuotedInBrl() && offer.getOperation().equals(SELL)) {
            this.bitcoinBalance = this.bitcoinBalance.subtract(offer.getBaseAmount());
            this.brlBalance = this.brlBalance.add(offer.getQuoteAmount());
        } else if (offer.getQuotedInBrl() && offer.getOperation().equals(BUY)) {
            this.bitcoinBalance = this.bitcoinBalance.subtract(offer.getQuoteAmount());
            this.brlBalance = this.brlBalance.add(offer.getBaseAmount());
        } else if (offer.getQuotedInBrl() && offer.getOperation().equals(SELL)) {
            this.brlBalance = this.brlBalance.subtract(offer.getBaseAmount());
            this.bitcoinBalance = this.bitcoinBalance.add(offer.getQuoteAmount());
        }
    }

    public void confirmOffer(Offer offer) {
        offer.confirm();

        removeBalance(offer);
    }

}
