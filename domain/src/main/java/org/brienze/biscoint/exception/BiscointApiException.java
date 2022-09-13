package org.brienze.biscoint.exception;

import org.springframework.http.HttpStatus;

public class BiscointApiException extends RuntimeException {

    private final HttpStatus statusResponse;
    private final String messageResponse;
    private final String message;

    public BiscointApiException(String message, String messageResponse, HttpStatus status) {
        super(message);

        this.message = message;
        this.messageResponse = messageResponse;
        this.statusResponse = status;
    }

    public HttpStatus getStatusResponse() {
        return statusResponse;
    }

    public String getMessageResponse() {
        return messageResponse;
    }

    public String getMessage() {
        return message;
    }

}
