package org.brienze.biscoint.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BiscointApiException {

	public ValidationException(String message) {
		super(message, message, HttpStatus.BAD_REQUEST);
	}

}
