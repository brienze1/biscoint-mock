package org.brienze.biscoint.exception;

import org.springframework.http.HttpStatus;

public class CredentialNotFoundException extends BiscointApiException {

	public CredentialNotFoundException(String msg) {
		super(msg, "Could not validate credential.", HttpStatus.UNAUTHORIZED);
	}
	
}
