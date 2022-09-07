package org.brienze.biscoint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="credentials")
public class CredentialEntity {

	@Id
	@NotEmpty
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
