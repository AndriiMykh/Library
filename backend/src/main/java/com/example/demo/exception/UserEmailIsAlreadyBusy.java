package com.example.demo.exception;

public class UserEmailIsAlreadyBusy extends RuntimeException{
	public UserEmailIsAlreadyBusy() {
	}

	public UserEmailIsAlreadyBusy(String message) {
		super(message);
	}

	public UserEmailIsAlreadyBusy(Throwable cause) {
		super(cause);
	}

	public UserEmailIsAlreadyBusy(String message, Throwable cause) {
		super(message, cause);
	}
}
