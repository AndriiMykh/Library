package com.example.demo.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Address {
	private String city;
	private String street;
	private String houseNumber;
	private String flat;
}
