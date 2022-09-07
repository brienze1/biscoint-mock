package org.brienze.biscoint.persistence;

import org.brienze.biscoint.adapter.ClientRepositoryAdapter;
import org.brienze.biscoint.exception.ClientNotFoundException;
import org.brienze.biscoint.model.Client;
import org.brienze.biscoint.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientPersistence implements ClientRepositoryAdapter {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client findClient(String apiKey) {
		return clientRepository.findById(apiKey).orElseThrow(() -> new ClientNotFoundException("Client not found.")).toClient();
	}

}
