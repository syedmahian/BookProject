package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {

	private Long bookId;
	private String title;
	private int pages;

	// this will spit out JSON

}
