package com.example.demo.exception;

public class BookNotAvailable extends RuntimeException{
	public BookNotAvailable() {
		
	}
	
	public BookNotAvailable(String message) {
		super(message);
	}
	
	public BookNotAvailable(String message,Throwable cause) {
		super(message,cause);

	}
}
