package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("SELECT u FROM Book u WHERE u.title LIKE CONCAT('%',:name,'%')")
	List<Book> findBooksByTitle(@Param("name")String name); 
	
	@Query("SELECT u FROM Book u WHERE u.title = :title")
	Optional<Book> findBookByTitle(@Param("title") String title);
	
}
