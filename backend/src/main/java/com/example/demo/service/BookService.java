package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
@Transactional
public class BookService {

	private final BookRepository bookRepository;
	
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> findAllBooks() {
    	return bookRepository.findAll();
    }
    
    public Book createBook(Book book) {
    	return bookRepository.save(book);
    }
    
    public List<Book> createBooks(List<Book> books) {
    	return bookRepository.saveAll(books);
    }
    
    public  Book updateBook(Book book) {
    	return bookRepository.save(book);
    }
	
}
