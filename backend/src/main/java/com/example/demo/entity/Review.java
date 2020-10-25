package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String review;
	@ManyToOne
	private Book books;
	public Review(long id, String review) {
		this.Id=id;
		this.review=review;
	}
}
