package org.brienze.biscoint.useCases;

import org.brienze.biscoint.adapter.CredentialRepositoryAdapter;
import org.brienze.biscoint.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignTokenUseCase {

	@Autowired
	private CredentialRepositoryAdapter credentialRepository;
	
	@Autowired
	private TokenService tokenService;
	
	public String signToken(Object body, String path, String nonce, String apiKey) {
		String secret = credentialRepository.getSecret(apiKey);
		
		return tokenService.signToken(body, path, nonce, secret);
	}
	
}
