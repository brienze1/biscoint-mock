package org.brienze.biscoint.cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.brienze.biscoint.cucumber.context.Context;
import org.brienze.biscoint.webservice.BiscointWebService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
public class CommonTestSteps {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private BiscointWebService biscointWebService;

    @Autowired
    private ObjectMapper mapper;

    @Given("the context is clean")
    public void theContextIsClean() {
        Context.getInstance().reset();
    }

    @Given("biscoint url is set correctly")
    public void biscointUrlIsSetCorrectly() {
        ReflectionTestUtils.setField(biscointWebService, "url", "http://localhost:" + serverPort + "/v2/ticker");
    }

    @Then("the return status code should be {int}")
    public void theReturnStatusCodeShouldBe(Integer statusCode) {
        Assert.assertEquals(statusCode, Context.getInstance().get("response_status", Integer.class));
    }

    @Then("the return body should not be null")
    public void theReturnBodyShouldNotBeNull() {
        Assert.assertNotNull(Context.getInstance().get("response", ResponseEntity.class).getBody());
    }

    @Then("the field {string} should not be empty")
    public void theFieldShouldNotBeEmpty(String fieldName) throws JsonProcessingException {
        String field = getField(fieldName, String.class);

        Assert.assertNotNull(field);
        Assert.assertFalse(field.isBlank());
    }

    @Then("the field {string} should be empty")
    public void theFieldShouldBeEmpty(String fieldName) throws JsonProcessingException {
        String field = getField(fieldName, String.class);

        Assert.assertNotNull(field);
        Assert.assertTrue(field.isBlank());
    }

    @Then("the field {string} should be null")
    public void theFieldShouldBeNull(String fieldName) throws JsonProcessingException {
        String field = getField(fieldName, String.class);

        Assert.assertNull(field);
    }

    @Then("the field {string} should be equal {string}")
    public void theFieldShouldBeEqual(String fieldName, String expectedValue) throws JsonProcessingException {
        String field = getField(fieldName, String.class);

        Assert.assertNotNull(field);
        Assert.assertEquals(expectedValue, field);
    }

    @Then("the field {string} should be false")
    public void theFieldShouldBe(String fieldName) throws JsonProcessingException {
        Boolean field = getField(fieldName, Boolean.class);

        Assert.assertNotNull(field);
        Assert.assertFalse(field);
    }

    @Then("the field {string} should be true")
    public void theFieldShouldBeTrue(String fieldName) throws JsonProcessingException {
        Boolean field = getField(fieldName, Boolean.class);

        Assert.assertNotNull(field);
        Assert.assertTrue(field);
    }

    @Then("the error message should be {string}")
    public void theErrorMessageShouldBe(String errorMessage) {
        HttpClientErrorException ex = Context.getInstance().get("response_exception", HttpClientErrorException.class);

        Assert.assertNotNull(ex);
        Assert.assertTrue(ex.getResponseBodyAsString().contains(errorMessage));
    }

    private <T> T getField(String composedFieldName, Class<T> clazz) throws JsonProcessingException {
        JsonNode json = mapper.readTree(mapper.writeValueAsString(Context.getInstance().get("response", ResponseEntity.class).getBody()));

        String[] fields = composedFieldName.split("\\.");

        for (String fieldName : fields) {
            json = json.get(fieldName);
        }

        if (json == null) {
            return null;
        }

        return mapper.readValue(json.toString(), clazz);
    }

}
