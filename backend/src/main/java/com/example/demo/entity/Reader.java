package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Reader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String email;
	private String password;
	@ManyToMany( fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH} )
	private List<Book> books=new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Review> reviews=new ArrayList<>();
	@Embedded
	private Address address;
	
	public boolean findBooksInsidePresentsById(Book checkBook){
		for (Book book : books) {
			if(book.getId()==checkBook.getId()) {
				return true;
			}		
		}
		System.out.println();
		return false;
	}
	public List<Book> deleteBookFromReaderById(Long id){
		books.removeIf(book->(book.getId()==id));
		return books;
	}
    public void addReview(Review review) {
    	reviews.add(review);
    	review.setReader(this);
    }

	
}
