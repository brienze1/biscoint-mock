package org.brienze.biscoint.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.validator.Validators;

import static org.brienze.biscoint.enums.Operation.*;
import static org.brienze.biscoint.enums.Quote.BRL;
import static org.brienze.biscoint.enums.Quote.BTC;

public class Bitcoin {

    private Quote base;
    private Quote quote;
    private BigDecimal volume;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal last;
    private BigDecimal buyValue;
    private BigDecimal sellValue;
    private BigDecimal buyValueQuoteAmountReference;
    private BigDecimal buyValueBaseAmountReference;
    private BigDecimal sellValueQuoteAmountReference;
    private BigDecimal sellValueBaseAmountReference;
    private LocalDateTime timestamp;

    public BigDecimal getValueForOperation(Operation operation, Quote quotedOn) {
        Validators.validateNotNull(operation, "operation cannot be null.");

        if ((operation.equals(BUY) && quotedOn.equals(BTC)) || (operation.equals(SELL) && quotedOn.equals(BRL))) {
            Validators.validateNotNull(buyValue, "buyValue cannot be null.");
            return buyValue;
        }

        Validators.validateNotNull(sellValue, "sellValue cannot be null.");
        return sellValue;
    }

    public void setBase(Quote base) {
        this.base = base;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public void setBuyValue(BigDecimal buyValue) {
        this.buyValue = buyValue;
    }

    public void setSellValue(BigDecimal sellValue) {
        this.sellValue = sellValue;
    }

    public void setBuyValueQuoteAmountReference(BigDecimal buyValueQuoteAmountReference) {
        this.buyValueQuoteAmountReference = buyValueQuoteAmountReference;
    }

    public void setBuyValueBaseAmountReference(BigDecimal buyValueBaseAmountReference) {
        this.buyValueBaseAmountReference = buyValueBaseAmountReference;
    }

    public void setSellValueQuoteAmountReference(BigDecimal sellValueQuoteAmountReference) {
        this.sellValueQuoteAmountReference = sellValueQuoteAmountReference;
    }

    public void setSellValueBaseAmountReference(BigDecimal sellValueBaseAmountReference) {
        this.sellValueBaseAmountReference = sellValueBaseAmountReference;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Quote getBase() {
        return base;
    }

    public Quote getQuote() {
        return quote;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLast() {
        return last;
    }

    public BigDecimal getBuyValue() {
        return buyValue;
    }

    public BigDecimal getSellValue() {
        return sellValue;
    }

    public BigDecimal getBuyValueQuoteAmountReference() {
        return buyValueQuoteAmountReference;
    }

    public BigDecimal getBuyValueBaseAmountReference() {
        return buyValueBaseAmountReference;
    }

    public BigDecimal getSellValueQuoteAmountReference() {
        return sellValueQuoteAmountReference;
    }

    public BigDecimal getSellValueBaseAmountReference() {
        return sellValueBaseAmountReference;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
