package org.brienze.biscoint.dto;

import org.brienze.biscoint.validator.Validators;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiscointDto {

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private BitcoinDto bitcoinDto;

	public String getMessage() {
		return message;
	}
	public BitcoinDto getBitcoinDto() {
		Validators.validateNotNull(this.bitcoinDto, "Bitcoin value could not be retrieved");
		return bitcoinDto;
	}
	
}
