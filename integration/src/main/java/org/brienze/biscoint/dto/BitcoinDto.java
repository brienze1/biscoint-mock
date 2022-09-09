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
        return new Bitcoin(this.base, this.quote, this.buyValue, this.sellValue);
    }

}
