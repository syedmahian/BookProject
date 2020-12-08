package com.example.demo.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // classes that represent tables in our DB
@Data
@NoArgsConstructor
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long libraryId;

	@NotNull
	public String name;
	

	@OneToMany(mappedBy = "library", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Book> book;

	public Library(Long libraryId, String name) {
		super();
		this.libraryId = libraryId;
		this.name = name;
	}

	public Library(String name) {
		super();
		this.name = name;
	}

}