package org.brienze.biscoint.useCases;

import com.fasterxml.jackson.databind.JsonNode;
import org.brienze.biscoint.adapter.ClientRepositoryAdapter;
import org.brienze.biscoint.adapter.CredentialRepositoryAdapter;
import org.brienze.biscoint.exception.AuthenticationException;
import org.brienze.biscoint.model.Balance;
import org.brienze.biscoint.model.Client;
import org.brienze.biscoint.model.Offer;
import org.brienze.biscoint.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientUseCase {

	@Autowired
	private CredentialRepositoryAdapter credentialRepository;
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private ClientRepositoryAdapter clientRepository;
	
	public String signToken(Object body, String path, String nonce, String apiKey) {
		String secret = credentialRepository.getSecret(apiKey);
		
		return tokenService.signToken(body, path, nonce, secret);
	}

	public void authorizeRequest(JsonNode body, String path, String nonce, String apiKey, String sign) {
		String apiSecret = credentialRepository.getSecret(apiKey);

		String signValidation = tokenService.signToken(body, path, nonce, apiSecret);

		if (!signValidation.equals(sign)) {
			throw new AuthenticationException("Unauthorized");
		}
	}

	public Balance getBalance(String apiKey) {
		Client client = clientRepository.findClient(apiKey);

		return new Balance(client);
	}
}
