package org.brienze.biscoint.model;

import java.math.BigDecimal;

import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.validator.Validators;

public class OfferRequest {

	private Operation operation;
	private BigDecimal amount;
	private Boolean quotedOnBrl;

	public void setOperation(Operation operation) {
		Validators.validateNotNull(operation, "operation cannot be null.");

		this.operation = operation;
	}
	public void setAmount(BigDecimal amount) {
		Validators.validateGreaterThanZero(amount, "amount cannot be less than zero.");

		this.amount = amount;
	}
	public void setQuotedOnBrl(Boolean quotedOnBrl) {
		Validators.validateNotNull(quotedOnBrl, "quotedOnBrl cannot be null.");

		this.quotedOnBrl = quotedOnBrl;
	}
	
	public Operation getOperation() {
		return operation;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public Boolean getQuotedOnBrl() {
		return quotedOnBrl;
	}

}
