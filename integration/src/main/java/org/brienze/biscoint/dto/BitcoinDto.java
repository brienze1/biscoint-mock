package org.brienze.biscoint.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.model.Bitcoin;
import org.brienze.biscoint.validator.Validators;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	public void setBase(Quote base) {
		Validators.validateNotNull(base, "base cannot be null.");
		this.base = base;
	}
	public void setQuote(Quote quote) {
		Validators.validateNotNull(quote, "quote cannot be null.");
		this.quote = quote;
	}
	public void setBuyValue(String buyValue) {
		Validators.validateDouble(buyValue, "buyValue is not a double value.");
		this.buyValue = BigDecimal.valueOf(Double.valueOf(buyValue));
	}
	public void setBuyValueQuoteAmountReference(String buyValueQuoteAmountReference) {
		Validators.validateDouble(buyValueQuoteAmountReference, "buyValueQuoteAmountReference is not a double value.");
		this.buyValueQuoteAmountReference = BigDecimal.valueOf(Double.valueOf(buyValueQuoteAmountReference));
	}
	public void setBuyValueBaseAmountReference(String buyValueBaseAmountReference) {
		Validators.validateDouble(buyValueBaseAmountReference, "buyValueBaseAmountReference is not a double value.");
		this.buyValueBaseAmountReference = BigDecimal.valueOf(Double.valueOf(buyValueBaseAmountReference));
	}
	public void setSellValue(String sellValue) {
		Validators.validateDouble(sellValue, "sellValue is not a double value.");
		this.sellValue = BigDecimal.valueOf(Double.valueOf(sellValue));;
	}
	public void setSellValueQuoteAmountReference(String sellValueQuoteAmountReference) {
		Validators.validateDouble(sellValueQuoteAmountReference, "sellValueQuoteAmountReference is not a double value.");
		this.sellValueQuoteAmountReference = BigDecimal.valueOf(Double.valueOf(sellValueQuoteAmountReference));;
	}
	public void setSellValueBaseAmountReference(String sellValueBaseAmountReference) {
		Validators.validateDouble(sellValueBaseAmountReference, "sellValueBaseAmountReference is not a double value.");
		this.sellValueBaseAmountReference = BigDecimal.valueOf(Double.valueOf(sellValueBaseAmountReference));;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		Validators.validateNotNull(timestamp, "timestamp cannot be null.");
		this.timestamp = timestamp;
	}
	
	public Bitcoin toBitcoin() {
		// TODO Auto-generated method stub
		return null;
	}

}
