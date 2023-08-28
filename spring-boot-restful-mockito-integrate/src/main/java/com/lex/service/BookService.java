package com.lex.service;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;

import java.util.List;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public class BookService {

	private BookRepository bookRepository;
	private EmailService emailService;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public BookService(BookRepository bookRepository, EmailService emailService) {
		this.bookRepository = bookRepository;
		this.emailService = emailService;
	}

	// Fake
	public void addBook(Book book){
		bookRepository.save(book);
	}

	public int findNumberOfBooks(){
		return bookRepository.findAll().size();
	}

	// Dummy

	// Stub
	public List<Book> getNewBooksWithAppliedDiscount(int discountRate, int days){
		List<Book> Books = bookRepository.findNewBooks(days);
		// 500 apply 10% -> 10% of 500 -> 50 => 500 - 50 = 450
		List<Book> newBooks = Books.stream().map(book -> {
			int price = book.getPrice();
			int newPrice = price - (discountRate * price / 100);
			book.setPrice(newPrice);
			return book;
		}).toList();

		return newBooks;
	}

	// Spy
	public void addBookBySpy(Book book){
		if (book.getPrice() > 400){
			return;
		}
		bookRepository.save(book);
	}

	// Mock
}
