package org.brienze.biscoint.useCases;

import java.math.BigDecimal;

import org.brienze.biscoint.adapter.BiscointWebServiceAdapter;
import org.brienze.biscoint.enums.Operation;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.model.Bitcoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BitcoinUseCase {

    @Autowired
    private BiscointWebServiceAdapter biscointWebServiceAdapter;

    public BigDecimal getUnitaryValue(Operation operation, Quote quotedOn) {
        Bitcoin bitcoin = biscointWebServiceAdapter.getBitcoinUnitaryValue();

        return bitcoin.getValueForOperation(operation, quotedOn);
    }

    public Bitcoin getUnitaryValue() {
        return biscointWebServiceAdapter.getBitcoinUnitaryValue();
    }

}
