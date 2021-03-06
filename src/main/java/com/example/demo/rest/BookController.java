package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDto;
import com.example.demo.persistence.domain.Book;
import com.example.demo.service.BookService;

@RestController
@CrossOrigin
@RequestMapping("/car") // this is to further define the path
public class BookController {

	private BookService service;

	@Autowired
	public BookController(BookService service) {
		super();
		this.service = service;
	}

	@GetMapping("/hello") // This is the mapping i want - Get me something
	public String hello() {
		return "hello from Car";
	}

	// pathVariable - this is the values in the URL "?id=2"
	// @RequestBody -- put/post - POST MEHTOD body of that request

	// pattern matching here url - /hello/<valuehere>
	@GetMapping("/hello/{id}")
	public String helloName(@PathVariable String BookId) {
		return "Hello " + BookId;
	}

	// @GetMapping("/num1/{num1}/num2/{num2}/num3/{num3}")
	@GetMapping("/num1/{num1}/num2/{num2}")
	public int addTwo(@PathVariable("num1") int number1, @PathVariable("num2") int number2) {
		return number1 + number2;
	}

	// Create method
	@PostMapping("/create")
	public ResponseEntity<BookDto> create(@RequestBody Book book) {
		BookDto created = this.service.create(book);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		// http status code - 201 (created)

	}

	// read all method
	@GetMapping("/read")
	public ResponseEntity<List<BookDto>> read() {
		return ResponseEntity.ok(this.service.readAll());
		// ok - 200
	}

	// read one
	@GetMapping("/read/{id}")
	public ResponseEntity<BookDto> readOne(@PathVariable Long bookId) {
		return ResponseEntity.ok(this.service.readOne(bookId));
	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<BookDto> update(@PathVariable Long bookId, @RequestBody BookDto carDto) {
		return new ResponseEntity<>(this.service.update(carDto, bookId), HttpStatus.ACCEPTED);
	}

	// Delete one
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BookDto> delete(@PathVariable Long bookId) {
		return this.service.delete(bookId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				// no_content - if deleted successfully then should return nothing
				: new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		// if the record isnt found!
	}
}
