package org.brienze.biscoint.useCases;

import org.brienze.biscoint.adapter.CredentialRepositoryAdapter;
import org.brienze.biscoint.exception.AuthenticationException;
import org.brienze.biscoint.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class AuthorizeRequestUseCase {

	@Autowired
	private CredentialRepositoryAdapter credentialRepository;
	
	@Autowired
	private TokenService tokenService;
	
	public void authorizeRequest(JsonNode body, String path, String nonce, String apiKey, String sign) {
		String apiSecret = credentialRepository.getSecret(apiKey);
		
		String signValidation = tokenService.signToken(body, path, nonce, apiSecret);
		
		if(!signValidation.equals(sign)) {
			throw new AuthenticationException("Unauthorized");
		}
	}


}
