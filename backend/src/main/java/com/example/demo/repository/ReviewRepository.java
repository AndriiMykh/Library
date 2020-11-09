package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Reader;
import com.example.demo.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	@Query("SELECT u FROM Review u WHERE u.book.id = :book")
	List<Review> findByBookId(@Param("book")Long bookId); 
	
	
}
