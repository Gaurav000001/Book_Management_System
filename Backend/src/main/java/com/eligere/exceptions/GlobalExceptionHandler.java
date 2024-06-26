package com.eligere.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	
	@ExceptionHandler(BookException.class)
	public ResponseEntity<ErrorDetails> activityExceptionHandler1(BookException ex, WebRequest w){
		
		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(w.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(Exception ex, WebRequest w) {

		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(w.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException ex, WebRequest w) {

		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(w.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {

		ErrorDetails err = new ErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDetails(ex.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
}
