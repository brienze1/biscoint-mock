package org.brienze.biscoint.exception;

import org.springframework.http.HttpStatus;

public class BiscointApiException extends RuntimeException {

	private HttpStatus statusResponse;
	private String messageResponse;
	private String message;
	
	public BiscointApiException(String message, String messageResponse, HttpStatus status) {
		super(message);
		
		this.message = message;
		this.messageResponse = messageResponse;
		this.statusResponse = status;
	}
	
	public HttpStatus getStatusResponse() {
		return statusResponse;
	}
	public void setStatusResponse(HttpStatus statusResponse) {
		this.statusResponse = statusResponse;
	}
	public String getMessageResponse() {
		return messageResponse;
	}
	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
