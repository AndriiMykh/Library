package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Column(length=10000)
	private String review;
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="reader_id")
	private Reader reader;
	public Review(long id, String review) {
		this.Id=id;
		this.review=review;
	}
	public Review(String review) {
		this.review=review;
	}
}
