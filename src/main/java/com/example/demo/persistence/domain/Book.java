package com.example.demo.persistence.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // classes that represent tables in our DB
@Data
@NoArgsConstructor
public class Book {

	// default constructor
	// all args constructor
	// getters
	// setters
	// toString
	// equals and hasCode

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@NotNull
	private String title;

	@NotNull
	private int pages;


	public Book(Long bookId, String title, int pages) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pages = pages;
		
	}

	public Book(String title, int pages) {
		super();
		this.title = title;
		this.pages = pages;
		
	}

}