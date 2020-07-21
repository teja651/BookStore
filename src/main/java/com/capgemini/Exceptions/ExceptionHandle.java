package com.capgemini.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandle {
	
	@ExceptionHandler(BookIdInvalid.class)
	public ResponseEntity<Object> handler(BookIdInvalid b)
	{
		
		
		
		
		return new ResponseEntity<Object>(new BookIdException(b.getMessage(),b),HttpStatus.BAD_REQUEST);
		
	}

}
