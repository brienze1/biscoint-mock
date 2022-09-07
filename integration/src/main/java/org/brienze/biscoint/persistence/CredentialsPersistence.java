package org.brienze.biscoint.persistence;

import java.util.Optional;

import org.brienze.biscoint.adapter.CredentialRepositoryAdapter;
import org.brienze.biscoint.entity.CredentialEntity;
import org.brienze.biscoint.exception.CredentialNotFoundException;
import org.brienze.biscoint.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CredentialsPersistence implements CredentialRepositoryAdapter {

	@Autowired
	private CredentialRepository credentialRepository;

	@Override
	public String getSecret(String apiKey) {
		Optional<CredentialEntity> credentialEntity = credentialRepository.findById(apiKey);

		return credentialEntity.orElseThrow(() -> new CredentialNotFoundException("ApiKey not found.")).getApi_secret();
	}

}
