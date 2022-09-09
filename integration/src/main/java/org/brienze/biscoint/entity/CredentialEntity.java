package org.brienze.biscoint.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="credentials")
public class CredentialEntity {

	@Id
	@Column(name="api_key")
	private String api_key;
	
	@NotEmpty
	@Column(name="api_secret", unique = true)
	private String api_secret;

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public void setApi_secret(String api_secret) {
		this.api_secret = api_secret;
	}
	public String getApi_key() {
		return api_key;
	}
	public String getApi_secret() {
		return api_secret;
	}
	
}
