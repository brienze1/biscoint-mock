package org.brienze.biscoint.exception;

import org.springframework.http.HttpStatus;

public class ClientNotFoundException extends BiscointApiException {

	public ClientNotFoundException(String message) {
		super(message, "Client not found.", HttpStatus.NOT_FOUND);
	}

}
