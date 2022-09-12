package org.brienze.biscoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponseDto {

    @JsonProperty("token")
    private String token;

    public TokenResponseDto(String token) {
        this.token = token;
    }

    public TokenResponseDto() {
    }

    public String getToken() {
        return token;
    }
}
