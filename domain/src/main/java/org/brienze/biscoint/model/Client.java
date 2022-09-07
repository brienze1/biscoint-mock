package org.brienze.biscoint.model;

import java.math.BigDecimal;

import org.brienze.biscoint.validator.Validators;

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
		Validators.validateGreaterThanZero(bitcoinBalance, "bitcoinBalance cannot be less than zero.");
		this.bitcoinBalance = bitcoinBalance;
	}
	public void setBrlBalance(BigDecimal brlBalance) {
		Validators.validateGreaterThanZero(brlBalance, "bitcoinBalance cannot be less than zero.");
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
	
	public boolean hasBalanceForTransaction(Offer offer) {
		Validators.validateGreaterThanZero(this.bitcoinBalance, "bitcoinBalance cannot be less than zero.");
		Validators.validateGreaterThanZero(this.brlBalance, "brlBalance cannot be less than zero.");
		Validators.validateNotNull(offer, "offer cannot be null.");
		Validators.validateGreaterThanZero(offer.getQuoteAmount(), "transaction value cannot be less than zero.");
		
		if(offer.getQuotedInBrl()) {
			Validators.validatePositive(this.brlBalance.compareTo(offer.getQuoteAmount()), "Brl balance cannot be less than transaction value.");
		} else {
			Validators.validatePositive(this.bitcoinBalance.compareTo(offer.getQuoteAmount()), "Bitcoin balance cannot be less than transaction value.");
		}
		
		return true;
	}
	
}
