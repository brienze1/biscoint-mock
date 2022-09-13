package org.brienze.biscoint.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.brienze.biscoint.exception.BiscointApiException;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandler {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ErrorHandler.class);

	@ExceptionHandler({BiscointApiException.class})
	public ResponseEntity<Object> biscointErrorHandler(BiscointApiException ex, WebRequest request) {
		log.error(ex.getMessage());
		
		Map<String, Object> errorResponse = new HashMap<>();

		errorResponse.put("timestamp", LocalDateTime.now().format(FORMATTER));
		errorResponse.put("status", ex.getStatusResponse().value());
		errorResponse.put("error", ex.getMessageResponse());
		errorResponse.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
		
		return ResponseEntity.status(ex.getStatusResponse()).body(errorResponse);
	}

	@ExceptionHandler({InvalidDataAccessApiUsageException.class})
	public ResponseEntity<Object> invalidDataAccessErrorHandler(InvalidDataAccessApiUsageException ex,
			WebRequest request) {
		log.error(ex.getMessage());

		Map<String, Object> errorResponse = new HashMap<>();

		errorResponse.put("timestamp", LocalDateTime.now().format(FORMATTER));
		errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
		errorResponse.put("error", "Request malformed.");
		errorResponse.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

}
