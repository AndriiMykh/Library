package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.entity.Category;
import com.example.demo.entity.Reader;
import com.example.demo.entity.Review;
import com.example.demo.exception.DataNotFound;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.ReaderService;
import com.example.demo.service.ReviewService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("http://localhost:4200")
public class BookController {
	
	private final BookService bookService;
	private final ReaderService readerService;
	private final ReviewService reviewService;
	
	public BookController(BookService bookService,ReaderService readerService,ReviewService reviewService) {
		this.readerService = readerService;
		this.bookService=bookService;
		this.reviewService=reviewService;
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
    
    @GetMapping("/categories/{categoryName}")
    public List<Book> getBookById(@PathVariable String categoryName) {
        return bookService.findByCategory(Category.valueOf(categoryName.toString().toUpperCase()));
    }
    
    @GetMapping("/categories")
    public Set<Category> getAllCategories() {
        return bookService.getAllCategories();
    }
    
    @GetMapping("/searchByKeyword/{keyword}")
    public List<Book> getByKeyword(@PathVariable String keyword) {
    	String capitalize = keyword.substring(0,1).toUpperCase()+keyword.substring(1,keyword.length()).toLowerCase();
        return bookService.findByTitle(capitalize);
    }
    
    @GetMapping("/getReviews/{id}")
    public List<Review> getAllReviews(@PathVariable Long id){
    	List<Review> reviews=reviewService.findAllReviewsToBook(id);
    	return reviews;
	
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
                	bookObj.setCategory(book.getCategory());
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
    public ResponseEntity<Book> addReview(@PathVariable Long bookId,@RequestParam("id") long id,@RequestParam("review") String review){
		Optional<Book> book = bookService.findBookById(bookId);
		Optional<Reader> reader = readerService.findById(id);
		System.out.println(review);
		if(book.isPresent()&&reader.isPresent()) {
			Review newReview = new Review();
			newReview.setReview(review);
			reader.get().addReview(newReview);
			book.get().addReview(newReview);
			bookService.updateBook(book.get());
			return ResponseEntity.ok(book.get());
		}else
			return ResponseEntity.notFound().build();
    }
	
	
}
