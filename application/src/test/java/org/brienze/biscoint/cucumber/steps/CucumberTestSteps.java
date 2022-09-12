package org.brienze.biscoint.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.brienze.biscoint.Application;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberTestSteps {

    @Given("the following data exist in credentials db")
    public void theFollowingDataExistInCredentialsDb() {
    }

    @And("the following payload is used")
    public void theFollowingPayloadIsUsed() {
    }

    @And("the nonce used is {int}")
    public void theNonceUsedIs(int arg0) {
    }

    @And("the path used is {string}")
    public void thePathUsedIs(String arg0) {
    }

    @When("the token is generated")
    public void theTokenIsGenerated() {
    }

    @Then("the return status code should be {int}")
    public void theReturnStatusCodeShouldBe(int arg0) {
    }

    @And("the token should be decoded using the secret {string}")
    public void theTokenShouldBeDecodedUsingTheSecret(String arg0) {
    }

    @And("the payload decoded should be")
    public void thePayloadDecodedShouldBe() {
    }

    @And("the path decoded should be {string}")
    public void thePathDecodedShouldBe(String arg0) {
    }
}




