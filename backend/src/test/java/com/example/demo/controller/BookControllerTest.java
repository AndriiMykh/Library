package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.ReaderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(controllers = BookController.class)
@MockBeans({@MockBean(BookService.class),@MockBean(ReaderService.class)})
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private ReaderService readerService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Book> bookList;
    
    @BeforeEach
    void setUp() {
        this.bookList = new ArrayList<>();
        bookList.add(new Book(1L,"Robinson Crusoe", "Book about man on island", 2, null, null, null));
        bookList.add(new Book(2L,"Tom sawyer", "Book about young boy and his friends", 5,null, null, null));
        bookList.add(new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null, null));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }
    
    @Test
    void shouldRetrieveAllBooks() throws Exception {
    	given(bookService.findAllBooks()).willReturn(bookList);
    	
    	this.mockMvc.perform(get("/api/books/"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.size()", is(bookList.size())));;
    }
    
    @Test
    void shouldFindBookById() throws Exception {
        final Long bookId = 1L;
        final Book book = new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null, null);
        
        given(bookService.findBookById(bookId)).willReturn(Optional.of(book));
        
        this.mockMvc.perform(get("/api/books/{id}",bookId))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$.title", is(book.getTitle())))
        	.andExpect(jsonPath("$.descr", is(book.getDescr())))
        	.andExpect(jsonPath("$.quantity", is(book.getQuantity()))); 	
    }
    
    @Test
    void shouldReturn404WhenBookIdDoesntExist() throws Exception {
    	final Long bookId = 1L;
    	
    	given(bookService.findBookById(bookId)).willReturn(Optional.empty());
    	
    	this.mockMvc.perform(get("/api/books/{id}",bookId))
    		.andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewBook() throws Exception {
    	
    	 given(bookService.createBook(any(Book.class))).willAnswer((invocation) -> invocation.getArgument(0));
    	 
    	 Book book=new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null, null);
    	     	 
    	 this.mockMvc.perform(post("/api/books/")
         .contentType(MediaType.APPLICATION_JSON)
         .content(objectMapper.writeValueAsString(book)))
         .andExpect(status().isCreated())
         .andExpect(jsonPath("$.title", is(book.getTitle())))
         .andExpect(jsonPath("$.descr", is(book.getDescr())));
    }
    

    @Test
    void updateBook() throws Exception {
        Long bookId = 1L;
        Book book = new Book(bookId,"Robinson Crusoe", "Book about man on island", 3,null, null, null);
        given(bookService.findBookById(bookId)).willReturn(Optional.of(book));
        given(bookService.updateBook(any(Book.class))).willAnswer((invocation) -> invocation.getArgument(0));
        
        this.mockMvc.perform(put("/api/books/{id}", book.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.descr", is(book.getDescr())));
    }
    
    @Test
    void shouldReturn404WhenUpdatingBookIdDoesntExist() throws Exception {
    	final Long bookId = 1L;
    	given(bookService.findBookById(bookId)).willReturn(Optional.empty());
    	 Book book=new Book(1L,"Robinson Crusoe", "Book about man on island", 3,null, null, null);
    	 
         this.mockMvc.perform(put("/api/books/{id}", bookId)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(book)))
         			.andExpect(status().isNotFound());
    }
    
    @Test
    void shouldDeleteBook() throws Exception {
        Long bookId = 1L;
        Book book=new Book(1L,"Robinson Crusoe", "Book about man on island", 3, null, null, null);
        given(bookService.findBookById(bookId)).willReturn(Optional.of(book));
        doNothing().when(bookService).deleteBookById(book.getId());
        
        
        this.mockMvc.perform(delete("/api/books/{id}",bookId))
					.andExpect(status().isOk());
    }
    
    @Test
    void shouldReturn404WhenDeletingNonExistingBook() throws Exception {
    	Long bookId = 1L;
    	given(bookService.findBookById(bookId)).willReturn(Optional.empty());

        this.mockMvc.perform(delete("/api/books/{id}",bookId))
                .andExpect(status().isNotFound());

    }
}
