package org.brienze.biscoint.cucumber.steps;

import io.cucumber.java.en.And;
import org.brienze.biscoint.entity.ClientEntity;
import org.brienze.biscoint.enums.Quote;
import org.brienze.biscoint.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class ClientTestSteps {

    @Autowired
    private ClientRepository clientRepository;

    private String apiKey;

    @And("a client with api_key {string} and name {string} exists in clients db")
    public void aClientWithApi_keyAndNameExistsInClientsDb(String apiKey, String name) {
        this.apiKey = apiKey;

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setApiKey(apiKey);
        clientEntity.setName(name);
        clientEntity.setBitcoinBalance(BigDecimal.ZERO);
        clientEntity.setBrlBalance(BigDecimal.ZERO);

        clientRepository.save(clientEntity);
    }

    @And("the client has {double} {string} balance")
    public void theClientHasBalance(double balance, String quote) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(apiKey);

        if (clientEntity.isEmpty()) {
            throw new RuntimeException("client not found");
        }

        if (quote.equalsIgnoreCase(Quote.BRL.name())) {
            clientEntity.get().setBrlBalance(BigDecimal.valueOf(balance));
        } else if (quote.equalsIgnoreCase(Quote.BTC.name())) {
            clientEntity.get().setBitcoinBalance(BigDecimal.valueOf(balance));
        }

        clientRepository.save(clientEntity.get());
    }

}
