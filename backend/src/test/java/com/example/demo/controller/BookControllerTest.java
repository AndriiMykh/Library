package com.example.demo.controller;



import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    
	@Mock                         
    private BookRepository bookRepository; 
   
	@InjectMocks
	private BookService bookService;
	
	@Test
	public void shouldReturnAll() {
		List<Book> datas = new ArrayList<>();
		datas.add(new Book(1L,"Robinson Crusoe", "Book about man on island", 2, null, null));
		datas.add(new Book(2L,"Robinson Crusoe", "Book about man on island", 5,null, null));
		datas.add(new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null));
        given(bookRepository.findAll()).willReturn(datas);
        
        List<Book> expected = bookService.findAllBooks();
        
        assertEquals(expected, datas);
        
	}
	
	@Test
	public void shouldSaveSuccesfullyOne() {
		Book book = new Book(1L,"Robinson Crusoe", "Book about man on island", 5, null, null);
		given(bookRepository.save(book)).willAnswer(answer->answer.getArgument(0));
        Book savedBook = bookService.createBook(book);
        
        assertThat(savedBook).isNotNull();
        
        verify(bookRepository).save(any(Book.class));
		
	}
	
	@Test
	public void shouldSaveSuccesfullyList() {
		List<Book> datas = new ArrayList<>();
		int expected=4;
		datas.add(new Book(1L,"Robinson Crusoe", "Book about man on island", 2, null, null));
		datas.add(new Book(2L,"Robinson Crusoe", "Book about man on island", 5,null, null));
		datas.add(new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null));
		datas.add(new Book(4L,"Robinson Crusoe", "Book about man on island", 8,null, null));
		given(bookRepository.saveAll(datas)).willAnswer(answer->answer.getArgument(0));
		
		List<Book> books = bookService.createBooks(datas); 
		System.out.println(books.size());
		assertEquals(books.size(), expected);
	
		 
	}
	
	
	
}
