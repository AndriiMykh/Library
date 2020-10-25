package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String title;
	private String descr;
	private int quantity;
	@OneToMany(mappedBy = "books", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Review> reviews=new ArrayList<>();
	@ManyToOne
	private Reader readers;
    public void addReview(Review review) {
    	reviews.add(review);
    	review.setBooks(this);
    }
}
