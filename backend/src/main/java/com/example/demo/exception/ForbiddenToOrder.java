package com.example.demo.exception;

import java.util.List;

import com.example.demo.entity.Book;

public class ForbiddenToOrder extends RuntimeException{
	public ForbiddenToOrder() {
		
	}
	
	public ForbiddenToOrder(String message) {
		super(message);
	}
	
	public ForbiddenToOrder(String message,Throwable cause) {
		super(message,cause);

	}

}
