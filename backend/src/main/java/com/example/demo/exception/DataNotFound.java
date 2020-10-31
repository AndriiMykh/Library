package com.example.demo.exception;

public class DataNotFound extends RuntimeException {
	public DataNotFound() {
	}

	public DataNotFound(String message) {
		super(message);
	}

	public DataNotFound(Throwable cause) {
		super(cause);
	}

	public DataNotFound(String message, Throwable cause) {
		super(message, cause);
	}
}
