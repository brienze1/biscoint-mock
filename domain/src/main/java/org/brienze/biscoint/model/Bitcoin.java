package org.brienze.biscoint.model;

import java.math.BigDecimal;

import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.enums.Quote;

public class Bitcoin {

	private Quote base;
	private Quote quote;
	private BigDecimal buyValue;
	private BigDecimal sellValue;

	public BigDecimal getValueForOperation(Operation operation, Quote quotedOn) {
		// operation not null
		// quotedOn not null
		// amount not null
		// quote not null

		return null;
	}

}
