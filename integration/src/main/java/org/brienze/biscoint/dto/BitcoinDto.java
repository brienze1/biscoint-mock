package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.model.Bitcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BitcoinDto {

    @JsonProperty("base")
    private Quote base;

    @JsonProperty("quote")
    private Quote quote;

    @JsonProperty("vol")
    private BigDecimal vol;

    @JsonProperty("low")
    private BigDecimal low;

    @JsonProperty("high")
    private BigDecimal high;

    @JsonProperty("last")
    private BigDecimal last;

    @JsonProperty("ask")
    private BigDecimal buyValue;

    @JsonProperty("askQuoteAmountRef")
    private BigDecimal buyValueQuoteAmountReference;

    @JsonProperty("askBaseAmountRef")
    private BigDecimal buyValueBaseAmountReference;

    @JsonProperty("bid")
    private BigDecimal sellValue;

    @JsonProperty("bidQuoteAmountRef")
    private BigDecimal sellValueQuoteAmountReference;

    @JsonProperty("bidBaseAmountRef")
    private BigDecimal sellValueBaseAmountReference;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    public Bitcoin toBitcoin() {
        Bitcoin bitcoin = new Bitcoin();
        bitcoin.setBase(this.base);
        bitcoin.setQuote(this.quote);
        bitcoin.setVolume(this.vol);
        bitcoin.setLow(this.low);
        bitcoin.setHigh(this.high);
        bitcoin.setLast(this.last);
        bitcoin.setBuyValue(this.buyValue);
        bitcoin.setSellValue(this.sellValue);
        bitcoin.setBuyValueQuoteAmountReference(this.buyValueQuoteAmountReference);
        bitcoin.setBuyValueBaseAmountReference(this.buyValueBaseAmountReference);
        bitcoin.setSellValueQuoteAmountReference(this.sellValueQuoteAmountReference);
        bitcoin.setSellValueBaseAmountReference(this.sellValueBaseAmountReference);
        bitcoin.setTimestamp(this.timestamp);

        return bitcoin;
    }

    public void setBase(Quote base) {
        this.base = base;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void setBuyValue(BigDecimal buyValue) {
        this.buyValue = buyValue;
    }

    public void setSellValue(BigDecimal sellValue) {
        this.sellValue = sellValue;
    }
}
