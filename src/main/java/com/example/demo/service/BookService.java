package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookDto;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.persistence.domain.Book;
import com.example.demo.persistence.repo.BookRepo;
import com.example.demo.utils.SpringBeanUtils;

@Service
public class BookService {

	// this is where our business logic will happen

//	this is also where CRUD logic will take place.

	// implements are crud functionality
	private BookRepo repo;

	// makes object mapping easy by automatically determining how one object model
	// maps to another.
	private ModelMapper mapper;

	// we create our mapToDto

	private BookDto mapToDTO(Book book) {
		return this.mapper.map(book, BookDto.class);
	}

	@Autowired
	public BookService(BookRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// Create
	public BookDto create(Book book) {
		return this.mapToDTO(this.repo.save(book));
	}

	// read all method
	public List<BookDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		// stream - returns a sequential stream considering collection as its source
		// map - used to map each element to its corresponding result
		// :: - for each e.g. Random random = new Random();
		// random.ints().limit(10).forEach(System.out::println);
		// Collectors - used to return a list or string
	}

	// read one method
	public BookDto readOne(Long bookId) {
		return this.mapToDTO(this.repo.findById(bookId).orElseThrow(BookNotFoundException::new));
	}

	// update
	public BookDto update(BookDto bookDto, Long bookId) {
		// check if record exists
		Book toUpdate = this.repo.findById(bookId).orElseThrow(BookNotFoundException::new);
		// set the record to update
		toUpdate.setTitle(bookDto.getTitle());
		// check update for any nulls
		SpringBeanUtils.mergeNotNull(bookDto, toUpdate);
		// retun the method from repo
		return this.mapToDTO(this.repo.save(toUpdate));

	}

	// what happenes when you try to merge null stuff?

	// Delete
	public boolean delete(Long bookId) {
		this.repo.deleteById(bookId);// true
		return !this.repo.existsById(bookId);// true
	}

}
