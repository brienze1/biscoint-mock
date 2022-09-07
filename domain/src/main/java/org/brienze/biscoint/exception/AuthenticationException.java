package org.brienze.biscoint.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends BiscointApiException {

	public AuthenticationException(String msg) {
		super(msg, "Could not validate credential.", HttpStatus.UNAUTHORIZED);
	}

}
