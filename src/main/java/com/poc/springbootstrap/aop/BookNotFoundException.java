package com.poc.springbootstrap.aop;

public class BookNotFoundException extends RuntimeException {
	 
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    // ...
}
