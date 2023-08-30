package com.lex.service;

import com.lex.entity.Book;

/**
 * @author : Lex Yu
 * @date : 2023/8/30
 */
public class BookManager {
	private BookService bookService;

	public BookManager(BookService bookService) {
		this.bookService = bookService;
	}

	public int appliedDiscountOnBook(Long bookId, int discountRate) {
		Book book = bookService.findBook(bookId); // We need to Mock
		int discountedPrice = bookService.getAppliedDiscount(book, discountRate); // We need to actually call
		return discountedPrice;
	}
}
