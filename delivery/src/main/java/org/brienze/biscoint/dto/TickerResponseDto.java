package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.brienze.biscoint.model.Bitcoin;

public class TickerResponseDto {

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private TickerDto ticker;

    public TickerResponseDto(Bitcoin bitcoin) {
        this.message = "";
        this.ticker = new TickerDto(bitcoin);
    }

}
