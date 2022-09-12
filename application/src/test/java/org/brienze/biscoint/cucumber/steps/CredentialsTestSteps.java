package org.brienze.biscoint.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.brienze.biscoint.Application;
import org.brienze.biscoint.cucumber.context.Context;
import org.brienze.biscoint.entity.CredentialEntity;
import org.brienze.biscoint.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

@CucumberContextConfiguration
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTestSteps {

    private String apiKey;
    private String apiSecret;

    @Autowired
    private CredentialRepository credentialRepository;

    @Given("the following data exist in credentials db")
    public void theFollowingDataExistInCredentialsDb(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        this.apiKey = data.get("api_key");
        this.apiSecret = data.get("api_secret");

        Context.getInstance().set("api_key", apiKey);
        Context.getInstance().set("api_secret", apiKey);

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setApiKey(apiKey);
        credentialEntity.setApiSecret(apiSecret);

        credentialRepository.save(credentialEntity);
    }
}
