package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.model.Bitcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TickerDto {

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

    public TickerDto(Bitcoin bitcoin) {
        this.base = bitcoin.getBase();
        this.quote = bitcoin.getQuote();
        this.vol = bitcoin.getVolume();
        this.low = bitcoin.getLow();
        this.high = bitcoin.getHigh();
        this.last = bitcoin.getLast();
        this.buyValue = bitcoin.getBuyValue();
        this.buyValueQuoteAmountReference = bitcoin.getBuyValueQuoteAmountReference();
        this.buyValueBaseAmountReference = bitcoin.getBuyValueBaseAmountReference();
        this.sellValue = bitcoin.getSellValue();
        this.sellValueQuoteAmountReference = bitcoin.getSellValueQuoteAmountReference();
        this.sellValueBaseAmountReference = bitcoin.getSellValueBaseAmountReference();
        this.timestamp = bitcoin.getTimestamp();
    }

}
