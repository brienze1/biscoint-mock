package org.brienze.biscoint.cucumber.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.brienze.biscoint.cucumber.context.Context;
import org.brienze.biscoint.dto.TokenResponseDto;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class GetTokenTestSteps {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private RestTemplate restTemplate;

    private String payload;
    private String nonce;
    private String path;

    @Before
    public void init() {

    }

    @Given("the following payload is used")
    public void theFollowingPayloadIsUsed(String payload) {
        this.payload = payload;
    }

    @Given("the nonce used is {string}")
    public void theNonceUsedIs(String nonce) {
        this.nonce = nonce;
    }

    @Given("the path used is {string}")
    public void thePathUsedIs(String path) {
        this.path = path;
    }

    @When("the token is generated")
    public void theTokenIsGenerated() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("BSCNT-NONCE", String.valueOf(this.nonce));
            headers.add("BSCNT-APIKEY", Context.getInstance().get("api_key", String.class));
            headers.add("BSCNT-PATH", this.path);
            headers.add("Content-Type", "application/json");

            HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);

            Context.getInstance().set("response", restTemplate.exchange(
                    "http://localhost:" + this.serverPort + "/v1/auth",
                    HttpMethod.POST,
                    httpEntity,
                    TokenResponseDto.class));
        } catch (Exception ex) {
            Context.getInstance().set("exception", ex);
        }
    }

    @Then("the token should be equal {string}")
    public void theTokenShouldBeEqual(String token) {
        @SuppressWarnings("unchecked")
        ResponseEntity<TokenResponseDto> response = (ResponseEntity<TokenResponseDto>) Context.getInstance().get("response", ResponseEntity.class);
        Assert.assertNotNull(response.getBody());
        Assert.assertNotNull(response.getBody().getToken());
        Assert.assertEquals(token, response.getBody().getToken());
    }

}
