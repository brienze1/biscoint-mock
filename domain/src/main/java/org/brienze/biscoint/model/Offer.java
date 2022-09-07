package org.brienze.biscoint.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.validator.Validators;

public class Offer {

	private String offerId;
	private Quote base;
	private Quote quote;
	private Operation operation;
	private Boolean quotedInBrl;
	private BigDecimal baseAmount;
	private BigDecimal unitaryValue;
	private BigDecimal quoteAmount;
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	private String apiKeyId;
	
	public Offer(OfferRequest offerRequest, String apiKey, BigDecimal bitcoinUnitaryValue) {
		setOfferId(UUID.randomUUID().toString());
		setBase(Quote.get(offerRequest.getQuotedOnBrl()));
		setQuote(Quote.get(!offerRequest.getQuotedOnBrl()));
		setOperation(offerRequest.getOperation());
		setQuotedInBrl(offerRequest.getQuotedOnBrl());
		setBaseAmount(offerRequest.getAmount());
		setUnitaryValue(bitcoinUnitaryValue);
		setCreatedAt(LocalDateTime.now());
		setExpiresAt(LocalDateTime.now().plus(Duration.ofSeconds(20)));
		setApiKeyId(apiKey);
		
		if(quotedInBrl) {
			setQuoteAmount(offerRequest.getAmount().divide(bitcoinUnitaryValue));
		} else {
			setQuoteAmount(bitcoinUnitaryValue.multiply(offerRequest.getAmount()));
		}
	}
	
	public Offer() {
	}

	public void setOfferId(String offerId) {
		Validators.validateNotEmpty(offerId, "OfferId cannot be empty.");
		this.offerId = offerId;
	}
	public void setBase(Quote base) {
		Validators.validateNotNull(base, "base cannot be null.");
		this.base = base;
	}
	public void setQuote(Quote quote) {
		Validators.validateNotNull(quote, "quote cannot be null.");
		this.quote = quote;
	}
	public void setOperation(Operation operation) {
		Validators.validateNotNull(operation, "operation cannot be null.");
		this.operation = operation;
	}
	public void setQuotedInBrl(Boolean quotedInBrl) {
		Validators.validateNotNull(quotedInBrl, "quotedInBrl cannot be null.");
		this.quotedInBrl = quotedInBrl;
	}
	public void setBaseAmount(BigDecimal baseAmount) {
		Validators.validateGreaterThanZero(baseAmount, "baseAmount cannot be less than zero.");
		this.baseAmount = baseAmount;
	}
	public void setUnitaryValue(BigDecimal unitaryValue) {
		Validators.validateGreaterThanZero(unitaryValue, "unitaryValue cannot be less than zero.");
		this.unitaryValue = unitaryValue;
	}
	public void setQuoteAmount(BigDecimal quoteAmount) {
		Validators.validateGreaterThanZero(quoteAmount, "quoteAmount cannot be less than zero.");
		this.quoteAmount = quoteAmount;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		Validators.validateNotNull(createdAt, "createdAt cannot be null.");
		this.createdAt = createdAt;
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		Validators.validateNotNull(expiresAt, "expiresAt cannot be null.");
		this.expiresAt = expiresAt;
	}
	public void setApiKeyId(String apiKeyId) {
		Validators.validateNotEmpty(apiKeyId, "apiKeyId cannot be empty.");
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
	public Boolean getQuotedInBrl() {
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
	public String getApiKeyId() {
		return apiKeyId;
	}
	
}
