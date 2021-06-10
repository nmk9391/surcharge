package com.assignment.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.excp.ResourceNotFoundExcp;

@ControllerAdvice
public class ExcpHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Please contact admin");
	}

	@ExceptionHandler(ResourceNotFoundExcp.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundExcp exception) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rate ID not found in RMS");
	}

}