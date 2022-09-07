package org.brienze.biscoint.exception;

import org.springframework.http.HttpStatus;

public class RequestBuildException extends BiscointApiException {

	public RequestBuildException(String msg) {
		super(msg, "Request malformed.", HttpStatus.BAD_REQUEST);
	}

}
