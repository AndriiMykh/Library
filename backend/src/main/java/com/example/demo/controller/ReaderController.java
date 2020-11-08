package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.entity.Reader;
import com.example.demo.exception.BookNotAvailable;
import com.example.demo.exception.ForbiddenToOrder;
import com.example.demo.service.BookService;
import com.example.demo.service.ReaderService;

@RestController
@RequestMapping("/api/readers")
@CrossOrigin("http://localhost:4200")
public class ReaderController {

	private final ReaderService readerService;

	private final BookService bookService;

	@Autowired
	public ReaderController(ReaderService readerService, BookService bookService) {
		this.readerService = readerService;
		this.bookService = bookService;
	}

	@GetMapping("/")
	private List<Reader> getAllReaders() {
		return readerService.findAllReaders();
	}

	@GetMapping("/{id}")
	private ResponseEntity<Reader> getReaderById(@PathVariable long id) {
		return readerService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/login")
	private Reader getReaderByEmailAndPassword(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		return readerService.findByEmailAndPassword(email, password);
	}

	@GetMapping("/getBooks/{id}")
	private List<Book> getReaderBooks(@PathVariable long id) {
		List<Book> books;
		Optional<Reader> reader = readerService.findById(id);
		if (reader.isPresent()) {
			books = reader.get().getBooks();
			return books;
		} else
			return null;
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	private Reader createReader(@RequestBody Reader reader) {
		return readerService.createReader(reader);
	}

	@PutMapping("/{id}")
	private ResponseEntity<Reader> updateReader(@PathVariable long id, @RequestBody Reader reader) {
		return readerService.findById(id).map(readerObj -> {
			readerObj.setId(id);
			readerObj.setAddress(reader.getAddress());
			readerObj.setBooks(reader.getBooks());
			readerObj.setEmail(reader.getEmail());
			readerObj.setPassword(reader.getPassword());
			return ResponseEntity.ok(readerService.updateReader(readerObj));
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Reader> deleteReader(@PathVariable Long id) {
		return readerService.findById(id).map(reader -> {
			readerService.deleteReaderById(id);
			return ResponseEntity.ok(reader);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/addBooksToReader/{userId}")
	private ResponseEntity<Reader> addBooksToReader(@PathVariable long userId, @RequestBody List<Book> books) {
		Optional<Reader> reader = readerService.findById(userId);
		if (reader.isPresent()) {
			final Reader readerWithBooks = reader.get();
			List<Book> forbiddenBooks = new ArrayList<Book>();
			readerWithBooks.getBooks().forEach(readerBook -> {
				books.forEach(orderBook -> {
					if ((orderBook.getId() - readerBook.getId()) == 0)
						forbiddenBooks.add(orderBook);
				});
			});
			if (forbiddenBooks.size() > 0) {
				String message = createMessage(forbiddenBooks);
				message += ("Because you already read it's.");
				throw new ForbiddenToOrder(message);
			}
			if (checkAvailability(books).size() > 0) {
				String message = createMessage(books);
				message += ("Because there are no available");
				throw new BookNotAvailable(message);
			}
			books.forEach(bookInside -> {
				Optional<Book> book = bookService.findBookById(bookInside.getId());
				if(book.isPresent()) {
					book.get().setQuantity(book.get().getQuantity()-1);
					bookService.updateBook(book.get());
					readerWithBooks.getBooks().add(book.get());
					readerService.updateReader(readerWithBooks);
				}
			});
			return ResponseEntity.ok(readerWithBooks);
		} else
			return ResponseEntity.notFound().build();

	}

	@PostMapping("/deleteBooksFromReader/{userId}")
	private ResponseEntity<Reader> deleteBooksFromReader(@PathVariable long userId, @RequestBody Book book) {
		Optional<Reader> reader = readerService.findById(userId);
		if (reader.isPresent()) {
			final Reader readerWithBooks = reader.get();
			readerWithBooks.getBooks().removeIf(bookInside -> (bookInside.getId() - book.getId()) == 0);
			book.setQuantity(book.getQuantity() + 1);
			bookService.updateBook(book);
			readerService.updateReader(readerWithBooks);
			return ResponseEntity.ok(readerWithBooks);
		} else
			return ResponseEntity.notFound().build();

	}

	private String createMessage(List<Book> books) {
		String newMessage;
		if (books.size() == 1)
			newMessage = "You can't order this book:\n";
		else
			newMessage = "You can't order these books:\n";
		for (Book book : books) {
			newMessage += book.getTitle();
			newMessage += ("\n");
		}
		return newMessage;
	}

	private List<Book> checkAvailability(List<Book> books) {
		List<Book> notAvailable = new ArrayList<Book>();
		books.forEach(bookCheck -> {
			if (bookCheck.getQuantity() <= 0) {
				notAvailable.add(bookCheck);
			}
		});
		return notAvailable;
	}
}
