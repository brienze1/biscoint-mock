package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.model.Balance;

public class BalanceDto {

    @JsonProperty("BTC")
    private String bitcoinBalance;

    @JsonProperty("BRL")
    private String brlBalance;

    public BalanceDto(Balance balance) {
        this.bitcoinBalance = balance.getBitcoinBalance().toPlainString();
        this.brlBalance = balance.getBrlBalance().toPlainString();
    }

    public BalanceDto() {
    }

}
