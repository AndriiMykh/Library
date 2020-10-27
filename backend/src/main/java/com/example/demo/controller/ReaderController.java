package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.entity.Reader;
import com.example.demo.service.ReaderService;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

	private final ReaderService readerService;
	
	@Autowired
	public ReaderController(ReaderService readerService) {
		this.readerService=readerService;
	}
	
	@GetMapping("/")
	private List<Reader> getAllReaders(){
		return readerService.findAllReaders();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Reader> getReaderById(long id){
		return readerService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@GetMapping("/login")
	private Optional<Reader> getReaderByEmailAndPassword(
			@RequestParam("email") String email,
			@RequestParam("password") String password){
		return readerService.findByEmailAndPassword(email, password);
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	private Reader createReader(@RequestBody Reader reader) {
		return readerService.createReader(reader);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Reader> updateReader(@PathVariable long id, @RequestBody Reader reader) {
        return readerService.findById(id)
                .map(readerObj -> {
                	readerObj.setId(id);
                	readerObj.setAddress(reader.getAddress());
                	readerObj.setBooks(reader.getBooks());
                	readerObj.setEmail(reader.getEmail());
                	readerObj.setPassword(reader.getPassword());
                    return ResponseEntity.ok(readerService.updateReader(readerObj));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
	
    @DeleteMapping("/{id}")
    private ResponseEntity<Reader> deleteReader(@PathVariable Long id){
    	return readerService.findById(id)
    				.map(reader -> {
    					readerService.deleteReaderById(id);
    					return ResponseEntity.ok(reader);
    				})
    				 .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/addBooksToReader/{userId}")
    private ResponseEntity<Reader> addBooksToReader(@PathVariable long userId,@RequestBody Set<Book> books) {
    	if(books.isEmpty()) 
    		throw new RuntimeException();
    	else {
    		Optional<Reader> reader = readerService.findById(userId);
    		if(reader.isPresent()) {
    			final Reader readerWithBooks = reader.get();
    			readerWithBooks.getBooks().forEach(bookInside->{
    				books.contains(bookInside);
    				System.out.println("Book inside message");
    			});
    			readerWithBooks.getBooks().addAll(books);
    			readerService.updateReader(readerWithBooks);
    			return ResponseEntity.ok(readerWithBooks);
    		}
    		else 
    			throw new RuntimeException();
    	}
		
    }
}

