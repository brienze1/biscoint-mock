package org.brienze.biscoint.adapter;

import org.brienze.biscoint.model.Client;

public interface ClientRepositoryAdapter {

	Client findClient(String apiKey);
	void save(Client client);

}
