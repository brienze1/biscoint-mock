package org.brienze.biscoint.handler;

import org.brienze.biscoint.exception.BiscointApiException;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler({BiscointApiException.class})
    public ResponseEntity<Object> biscointErrorHandler(BiscointApiException ex, WebRequest request) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(ex.getStatusResponse())
                .body(handleError(ex, ((ServletWebRequest) request).getRequest().getRequestURI()));
    }

    public Map<String, Object> handleError(BiscointApiException ex, String path) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("timestamp", LocalDateTime.now().format(FORMATTER));
        errorResponse.put("status", ex.getStatusResponse().value());
        errorResponse.put("error", ex.getMessageResponse());
        errorResponse.put("path", path);

        return errorResponse;
    }

}
