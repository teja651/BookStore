package com.capgemini.Exceptions;

import org.springframework.http.HttpStatus;

public class BookIdException {
	
	private final String message;
	
	private final Throwable throwable;
	
	

	public BookIdException(String message, Throwable throwable) {
		super();
		this.message = message;
		this.throwable = throwable;
	
	}

	public String getMessage() {
		return message;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	
	
	

}
