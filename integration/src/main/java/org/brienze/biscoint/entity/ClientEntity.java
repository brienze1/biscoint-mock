package org.brienze.biscoint.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.brienze.biscoint.model.Client;

@Entity
@Table(name = "clients")
public class ClientEntity {

    public ClientEntity(Client client) {
        this.apiKey = client.getApiKey();
        this.name = client.getName();
        this.bitcoinBalance = client.getBitcoinBalance();
        this.brlBalance = client.getBrlBalance();
    }

    @Id
    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "name")
    private String name;

    @Column(name = "bitcoin_balance", columnDefinition = "DECIMAL(16,8)")
    private BigDecimal bitcoinBalance;

    @Column(name = "brl_balance")
    private BigDecimal brlBalance;

    public ClientEntity() {
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBitcoinBalance(BigDecimal bitcoinBalance) {
        this.bitcoinBalance = bitcoinBalance;
    }

    public void setBrlBalance(BigDecimal brlBalance) {
        this.brlBalance = brlBalance;
    }

    public BigDecimal getBitcoinBalance() {
        return bitcoinBalance;
    }

    public BigDecimal getBrlBalance() {
        return brlBalance;
    }

    public Client toClient() {
        Client client = new Client();

        client.setApiKey(this.apiKey);
        client.setName(this.name);
        client.setBitcoinBalance(this.bitcoinBalance);
        client.setBrlBalance(this.brlBalance);

        return client;
    }

}
