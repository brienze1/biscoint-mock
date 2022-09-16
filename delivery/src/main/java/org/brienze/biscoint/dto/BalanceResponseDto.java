package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.model.Balance;

public class BalanceResponseDto {

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private BalanceDto balance;

    public BalanceResponseDto(Balance balance) {
        this.message = "";
        this.balance = new BalanceDto(balance);
    }

    public BalanceResponseDto() {
    }
}
