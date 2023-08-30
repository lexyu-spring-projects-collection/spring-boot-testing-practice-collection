package com.lex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	private Long bookId;
	private String title;
	private int price;
	private LocalDate publishedDate;
	private boolean isDigital;

	public Book(Long bookId, String title, int price, LocalDate publishedDate) {
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.publishedDate = publishedDate;
	}
}
