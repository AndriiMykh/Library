package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

	@Query("SELECT u FROM Reader u WHERE u.email = :email and u.password= :password")
	Optional<Reader> findByEmailAndPassword(@Param("email")String email, @Param("password")String password); 
	
	@Query("SELECT u FROM Reader u WHERE u.email = :email")
	Optional<Reader> findByEmail(@Param("email")String email); 
}
