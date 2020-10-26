package com.example.demo.exception;

public class BookAlreadyExists extends RuntimeException {
	public BookAlreadyExists() {
	}

	public BookAlreadyExists(String message) {
		super(message);
	}

	public BookAlreadyExists(Throwable cause) {
		super(cause);
	}

	public BookAlreadyExists(String message, Throwable cause) {
		super(message, cause);
	}
}
