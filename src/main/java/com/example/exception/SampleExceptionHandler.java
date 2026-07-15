package com.example.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SampleExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity  handleUserException(UserNotFoundException exception) {
		
		System.out.println(" Controller UserNotFoundException block........");
		Map<String, Object>response=new HashMap<>();
		response.put("Status", HttpStatus.NOT_FOUND);
		response.put("message", exception.getMessage());
		return ResponseEntity.ok().body(response);
		
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception) {
		System.out.println(" Exception block........");
		return exception.getMessage();
	}

}