package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BookService bookService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Book> bookList;
    
    @BeforeEach
    void setUp() {
        this.bookList = new ArrayList<>();
        bookList.add(new Book(1L,"Robinson Crusoe", "Book about man on island", 2, null, null));
        bookList.add(new Book(2L,"Tom sawyer", "Book about young boy and his friends", 5,null, null));
        bookList.add(new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }
    
    @Test
    void shouldRetrieveAllUsers() throws Exception {
    	given(bookService.findAllBooks()).willReturn(bookList);
    	
    	this.mockMvc.perform(get("/api/books/"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.size()", is(bookList.size())));;
    }
    
    @Test
    void shouldFindBookById() throws Exception {
        final Long bookId = 1L;
        final Book book = new Book(3L,"Robinson Crusoe", "Book about man on island", 3,null, null);
        
        given(bookService.findBookById(bookId)).willReturn(Optional.of(book));
        
        this.mockMvc.perform(get("/api/books/{id}",bookId))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$.title", is(book.getTitle())))
        	.andExpect(jsonPath("$.descr", is(book.getDescr())))
        	.andExpect(jsonPath("$.reviews", is(book.getReviews())));
        	
    }

}
