package com.cognixia.jump.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFound( UserNotFoundException e, WebRequest request ) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false) );
		
		return ResponseEntity.status(404).body(errorDetails);
	}
}
