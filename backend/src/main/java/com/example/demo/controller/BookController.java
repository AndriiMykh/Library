package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired 
	private BookRepository bookRepository;
	
	@GetMapping("/")
	private Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
}
