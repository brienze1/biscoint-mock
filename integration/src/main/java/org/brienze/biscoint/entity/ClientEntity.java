package org.brienze.biscoint.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.brienze.biscoint.model.Client;

@Entity
@Table(name="clients")
public class ClientEntity {

	@Id
	@NotEmpty
	@Column(name="api_key")
	private String api_key;
	
	@NotEmpty
	@Column(name="name")
	private String name;
	
	@PositiveOrZero
	@Column(name="bitcoin_balance")
	private BigDecimal bitcoinBalance;

	@PositiveOrZero
	@Column(name="brl_balance")
	private BigDecimal brlBalance;

	public void setApi_key(String api_key) {
		this.api_key = api_key;
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
	
	public String getApi_key() {
		return api_key;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getBitcoinBalance() {
		return bitcoinBalance;
	}
	public BigDecimal getBrlBalance() {
		return brlBalance;
	}
	
	public Client toClient() {
		Client client = new Client();
		
		client.setApiKey(this.api_key);
		client.setName(this.name);
		client.setBitcoinBalance(this.bitcoinBalance);
		client.setBrlBalance(this.brlBalance);
		
		return client;
	}
	
}
