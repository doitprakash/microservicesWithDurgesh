package com.main.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.main.user.service.payload.ApiResponse;

@RestControllerAdvice //Will handle exception wherever it happens globally
public class GlobalExceptionHandler {
     
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>handlerResourceNotFound(ResourceNotFoundException resourceNotFoundException){
		
		String message = resourceNotFoundException.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ApiResponse>usernotFound(UserIdNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse response =ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).success(true).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
}
