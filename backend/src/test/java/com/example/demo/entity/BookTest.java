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
	
	
	@Test
	public void setReviews() {
		Review review = new Review(1L, "Very interesting and fascinateing book!!!");
		Review review2 = new Review(2L, "Very bad and boring book! Dont waste your money");
		Review review3 = new Review(3L, "Nice to read.");
//		book.addReview(review);
//		book.addReview(review2);
//		book.addReview(review3);
//		assertEquals(3, book.getReviews().size());
	}
	
	
}
