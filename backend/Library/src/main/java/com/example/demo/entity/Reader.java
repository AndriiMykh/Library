package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Reader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String email;
	private String password;
	@OneToMany
	private List<Book> books=new ArrayList<>();
	@Embedded
	private Address address;
	
}
