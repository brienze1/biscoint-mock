package org.brienze.biscoint.cucumber.steps;

import io.cucumber.java.en.When;
import org.brienze.biscoint.cucumber.context.Context;
import org.brienze.biscoint.dto.BalanceResponseDto;
import org.brienze.biscoint.dto.OfferResponseDto;
import org.brienze.biscoint.useCases.ClientUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@SpringBootTest
public class GetBalanceTestSteps {

    private static final String GET_BALANCE_PATH = "v1/balance";
    private static final String NONCE = "123456789";

    @LocalServerPort
    private int serverPort;

    @Autowired
    private ClientUseCase signTokenUseCase;

    @Autowired
    private RestTemplate restTemplate;

    @When("get balance is called")
    public void getBalanceIsCalled() {
        Context.getInstance().set("token", signTokenUseCase.signToken(
                new HashMap<>(),
                GET_BALANCE_PATH,
                NONCE,
                Context.getInstance().get("api_secret", String.class)));

        getBalance();
    }

    @When("get balance is called without token")
    public void getBalanceIsCalledWithoutToken() {
        Context.getInstance().set("token", "");

        getBalance();
    }

    private void getBalance() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("BSCNT-NONCE", NONCE);
            headers.add("BSCNT-APIKEY", Context.getInstance().get("api_key", String.class));
            headers.add("BSCNT-SIGN", Context.getInstance().get("token", String.class));
            headers.add("Content-Type", "application/json");

            HttpEntity<?> httpEntity = new HttpEntity<>(new HashMap<>(), headers);

            ResponseEntity<BalanceResponseDto> response = restTemplate.exchange(
                    "http://localhost:" + this.serverPort + "/" + GET_BALANCE_PATH,
                    HttpMethod.POST,
                    httpEntity,
                    BalanceResponseDto.class);

            Context.getInstance().set("response_status", response.getStatusCodeValue());
            Context.getInstance().set("response", response);
        } catch (HttpClientErrorException ex) {
            Context.getInstance().set("response_exception", ex);
            Context.getInstance().set("response_status", ex.getRawStatusCode());
        }
    }
}
