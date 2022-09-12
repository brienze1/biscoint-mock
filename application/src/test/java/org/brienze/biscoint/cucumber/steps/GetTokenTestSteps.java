package org.brienze.biscoint.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.brienze.biscoint.Application;
import org.brienze.biscoint.dto.TokenResponseDto;
import org.brienze.biscoint.entity.CredentialEntity;
import org.brienze.biscoint.repository.CredentialRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Map;

@CucumberContextConfiguration
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetTokenTestSteps {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private RestTemplate restTemplate;

    private String apiKey;
    private String apiSecret;
    private String payload;
    private String nonce;
    private String path;
    private ResponseEntity<TokenResponseDto> response;
    private HttpStatusCodeException ex;

    @Before
    public void init() {

    }

    @Given("the following data exist in credentials db")
    public void theFollowingDataExistInCredentialsDb(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        this.apiKey = data.get("api_key");
        this.apiSecret = data.get("api_secret");

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setApiKey(apiKey);
        credentialEntity.setApiSecret(apiSecret);

        credentialRepository.save(credentialEntity);
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
            headers.add("BSCNT-APIKEY", this.apiKey);
            headers.add("BSCNT-PATH", this.path);
            headers.add("Content-Type", "application/json");

            HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);

            this.response = restTemplate.exchange("http://localhost:" + this.serverPort + "/v1/auth", HttpMethod.POST, httpEntity, TokenResponseDto.class);
        } catch (HttpStatusCodeException ex) {
            this.ex = ex;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Then("the return status code should be {int}")
    public void theReturnStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCodeValue());
    }

    @Then("the token should be equal {string}")
    public void theTokenShouldBeEqual(String token) {
        Assert.assertNotNull(response.getBody());
        Assert.assertNotNull(response.getBody().getToken());
        Assert.assertEquals(token, response.getBody().getToken());
    }

}




