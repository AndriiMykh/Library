package com.example.demo.repository;



import com.example.demo.entity.Book;
import com.example.demo.exception.BookAlreadyExists;
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
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {
    
	@Mock                         
    private BookRepository bookRepository; 
   
	@InjectMocks
	private BookService bookService;
	
	@Test
	public void shouldReturnAll() {
		List<Book> datas = new ArrayList<>();
		datas.add(new Book(1L,"Robinson Crusoe", "Book about man on island", 2, null, null, null));
		datas.add(new Book(2L,"Robinson Crusoe", "Book about man on island", 5,null, null, null));
		datas.add(new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null, null));
        given(bookRepository.findAll()).willReturn(datas);
        
        List<Book> expected = bookService.findAllBooks();
        
        assertEquals(expected, datas);
        
	}
	
	@Test
	public void shouldSaveSuccesfullyOne() {
		Book book = new Book(1L,"Robinson Crusoe", "Book about man on island", 5, null, null, null);
		given(bookRepository.save(book)).willAnswer(answer->answer.getArgument(0));
        Book savedBook = bookService.createBook(book);
        
        assertThat(savedBook).isNotNull();
        
        verify(bookRepository).save(any(Book.class));
		
	}
	
	@Test
	public void shouldSaveSuccesfullyList() {
		List<Book> datas = new ArrayList<>();
		int expected=4;
		datas.add(new Book(1L,"Robinson Crusoe", "Book about man on island", 2, null, null, null));
		datas.add(new Book(2L,"Robinson Crusoe", "Book about man on island", 5,null, null, null));
		datas.add(new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null, null));
		datas.add(new Book(4L,"Robinson Crusoe", "Book about man on island", 8,null, null, null));
		given(bookRepository.saveAll(datas)).willAnswer(answer->answer.getArgument(0));
		
		List<Book> books = bookService.createBooks(datas); 
		
		assertEquals(books.size(), expected);
	
	}
	
	@Test
	public void updateBook() {
		Book book = new Book(1L,"Robinson Crusoe", "Book about man on island", 5, null, null, null);
		
		given(bookRepository.save(book)).willReturn(book);
		
		Book expected = bookService.updateBook(book);
		
		assertThat(expected).isNotNull();
		
		verify(bookRepository).save(any(Book.class));
	}
	
	@Test
	public void deleteBook() {
		Long bookId = 1L;
		
		bookService.deleteBookById(bookId);
		
		verify(bookRepository, times(1)).deleteById(bookId);
	}
	
	@Test
	public void shouldFindBookByTitle() {
		List<Book> datas = new ArrayList<>();
		datas.add(new Book(2L,"tom sawyer", "Book about boy", 5,null, null, null));
		datas.add(new Book(4L,"tom sawyer", "Book about boy", 8,null, null, null));
		
		String title = "tom";
		given(bookRepository.findBooksByTitle(title)).willReturn(datas);
		
		List<Book> books=bookService.findByTitle(title);
		
		assertThat(books).isNotNull();
		
		assertEquals(datas, books);
		
	}
	
	@Test
	void shouldThrowBookAlreadyExists() {
		Book book = new Book(1L,"Robinson Crusoe", "Book about man on island", 5, null, null, null);
		
		String title = "Robinson Crusoe";
		given(bookRepository.findBookByTitle(title)).willReturn(Optional.of(book));
		
		assertThrows(BookAlreadyExists.class,()->{
			bookService.createBook(book);
		});
		
		verify(bookRepository,never()).save(any(Book.class));
		
		
	}
	
	
}
