package org.brienze.biscoint.model;

import org.brienze.biscoint.validator.Validators;

import java.math.BigDecimal;

public class Balance {

    private final BigDecimal bitcoinBalance;
    private final BigDecimal brlBalance;

    public Balance(Client client) {
        Validators.validatePositive(client.getBitcoinBalance(), "Bitcoin balance must be positive.");
        this.bitcoinBalance = client.getBitcoinBalance();

        Validators.validatePositive(client.getBrlBalance(), "Brl balance must be positive.");
        this.brlBalance = client.getBrlBalance();
    }

    public BigDecimal getBitcoinBalance() {
        return bitcoinBalance;
    }

    public BigDecimal getBrlBalance() {
        return brlBalance;
    }

}
