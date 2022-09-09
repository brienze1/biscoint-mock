package org.brienze.biscoint.model;

import java.math.BigDecimal;

import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.validator.Validators;

import static org.brienze.biscoint.enums.Operation.*;
import static org.brienze.biscoint.enums.Quote.BRL;
import static org.brienze.biscoint.enums.Quote.BTC;

public class Bitcoin {

    private final Quote base;
    private final Quote quote;
    private final BigDecimal buyValue;
    private final BigDecimal sellValue;

    public Bitcoin(Quote base, Quote quote, BigDecimal buyValue, BigDecimal sellValue) {
        this.base = base;
        this.quote = quote;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }

    public BigDecimal getValueForOperation(Operation operation, Quote quotedOn) {
        Validators.validateNotNull(operation, "operation cannot be null.");

        if ((operation.equals(BUY) && quotedOn.equals(BTC)) || (operation.equals(SELL) && quotedOn.equals(BRL))) {
            Validators.validateNotNull(buyValue, "buyValue cannot be null.");
            return buyValue;
        }

        Validators.validateNotNull(sellValue, "sellValue cannot be null.");
        return sellValue;
    }

}
