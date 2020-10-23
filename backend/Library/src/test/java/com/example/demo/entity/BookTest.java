package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

	private Book book;
	
	@BeforeEach
	public void initBook() {
		book=new Book();
	}
	
	@Test
	public void setTitle() {
		book.setTitle("Robinson Crusoe");
		assertEquals("Robinson Crusoe", book.getTitle());
	}
	
	@Test
	public void setDescr() {
		book.setDescr("Book about life one person on island.");
		assertEquals("Book about life one person on island.", book.getDescr());
	}
	
	
}
