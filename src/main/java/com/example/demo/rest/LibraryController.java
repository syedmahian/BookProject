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


import com.example.demo.dto.LibraryDto;
import com.example.demo.persistence.domain.Library;
import com.example.demo.service.LibraryService;

@RestController
@CrossOrigin
@RequestMapping("/garage") // this is to further define the path
public class LibraryController {

	private LibraryService service;

	@Autowired
	public LibraryController(LibraryService service) {
		super();
		this.service = service;
	}
//
//	@GetMapping("/hello") // This is the mapping i want - Get me something
//	public String hello() {
//		return "hello from car";
//	}
//
//	// pathVariable - this is the values in the URL "?id=2"
//	// @RequestBody -- put/post - POST MEHTOD body of that request
//
//	// pattern matching here url - /hello/<valuehere>
//	@GetMapping("/hello/{id}")
//	public String helloName(@PathVariable String id) {
//		return "Hello " + id;
//	}
//
//	// @GetMapping("/num1/{num1}/num2/{num2}/num3/{num3}")
//	@GetMapping("/num1/{num1}/num2/{num2}")
//	public int addTwo(@PathVariable("num1") int number1, @PathVariable("num2") int number2) {
//		return number1 + number2;
//	}

	// Create method
	@PostMapping("/create")
	public ResponseEntity<LibraryDto> create(@RequestBody Library garage) {
		LibraryDto created = this.service.create(garage);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		// http status code - 201 (created)

	}

	// read all method
	@GetMapping("/read")
	public ResponseEntity<List<LibraryDto>> read() {
		return ResponseEntity.ok(this.service.readAll());
		// ok - 200
	}

	// read one
	@GetMapping("/read/{id}")
	public ResponseEntity<LibraryDto> readOne(@PathVariable Long libraryId) {
		return ResponseEntity.ok(this.service.readOne(libraryId));
	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<LibraryDto> update(@PathVariable Long libraryId, @RequestBody LibraryDto libraryDto) {
		return new ResponseEntity<>(this.service.update(libraryDto, libraryId), HttpStatus.ACCEPTED);
	}

	// Delete one
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<LibraryDto> delete(@PathVariable Long libraryId) {
		return this.service.delete(libraryId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				// no_content - if deleted successfully then should return nothing
				: new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		// if the record isnt found!
	}
}