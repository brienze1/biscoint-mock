package org.brienze.biscoint.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "credentials")
public class CredentialEntity {

    @Id
    @Column(name = "api_key")
    private String apiKey;

    @NotEmpty
    @Column(name = "api_secret", unique = true)
    private String apiSecret;

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getApiSecret() {
        return apiSecret;
    }

}
