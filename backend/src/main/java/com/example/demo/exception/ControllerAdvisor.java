package com.example.demo.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserEmailIsAlreadyBusy.class)
	public ResponseEntity<Object> handleUserEmailIsAlreadyBusyException(UserEmailIsAlreadyBusy ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User email is already busy");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(BookAlreadyExists.class)
	public ResponseEntity<Object> handleBookAlreadyExistsException(BookAlreadyExists ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Such a book already exist");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentTypeMismatchException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler
	public ResponseEntity<Object> DataNotFoundException(DataNotFound ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No data found");
        
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
}
