package org.brienze.biscoint.exception;

import org.springframework.http.HttpStatus;

public class CredentialException extends BiscointApiException {

	public CredentialException(String msg) {
		super(msg, "Could not validate credential.", HttpStatus.UNAUTHORIZED);
	}

}
