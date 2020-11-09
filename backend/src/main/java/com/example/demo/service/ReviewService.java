package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Review;
import com.example.demo.exception.DataNotFound;
import com.example.demo.repository.ReaderRepository;
import com.example.demo.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    
    public List<Review> findAllReviewsToBook(Long bookId){
    	return reviewRepository.findByBookId(bookId);
    }
    
}
