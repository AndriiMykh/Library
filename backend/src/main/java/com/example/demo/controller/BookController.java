package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.entity.Review;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService=bookService;
	}
	
	@GetMapping("/")
	private Iterable<Book> getAllBooks() {
		return bookService.findAllBooks();
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.findBookById(id)
                .map(bookObj -> {
                	bookObj.setId(id);
                	bookObj.setDescr(book.getDescr());
                	bookObj.setQuantity(book.getQuantity());
                	bookObj.setTitle(book.getTitle());
                	bookObj.setReviews(book.getReviews());
                    return ResponseEntity.ok(bookService.updateBook(bookObj));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
    	return bookService.findBookById(id)
    				.map(book -> {
    					bookService.deleteBookById(id);
    					return ResponseEntity.ok(book);
    				})
    				 .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/addReviewToBook/{bookId}")
    public ResponseEntity<Book> addComment(@PathVariable Long bookId,@RequestBody String review){
		Optional<Book> book = bookService.findBookById(bookId);
		if(book.isPresent()) {
			Review newReview = new Review(review);
			book.get().addReview(newReview);
			bookService.updateBook(book.get());
			return ResponseEntity.ok(book.get());
		}else
			return ResponseEntity.notFound().build();
    }
	
	
}
