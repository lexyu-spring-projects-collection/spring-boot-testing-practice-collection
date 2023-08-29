package com.lex.service;

import com.lex.entity.Book;
import com.lex.entity.BookRequest;
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
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	public int findNumberOfBooks() {
		return bookRepository.findAll().size();
	}

	// Dummy

	// Stub
	public List<Book> getNewBooksWithAppliedDiscount(int discountRate, int days) {
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
	public void addBookBySpy(Book book) {
		if (book.getPrice() > 400) {
			return;
		}
		bookRepository.save(book);
	}

	// Mock


	// stubbing
	public int calculateTotalCost(List<Long> bookIds) {

		int total = 0;
		for (Long id : bookIds) {
			Book book = bookRepository.findBookById(id);
			total += book.getPrice();
		}
		return total;
	}

	public void addBookByStubbing(Book book) {
		bookRepository.save(book);
	}

	public void addBookByStubbing(BookRequest bookRequest) {
		Book book =
				new Book(null, bookRequest.getTitle(), bookRequest.getPrice(), bookRequest.getPublishedDate());
		bookRepository.save(book);
	}

	public void addBookByStubbingWithGreaterPrice(BookRequest bookRequest) {
		if (bookRequest.getPrice() <= 500){
			return;
		}
		Book book =
				new Book(null, bookRequest.getTitle(), bookRequest.getPrice(), bookRequest.getPublishedDate());
		bookRepository.save(book);
	}

	// behavior verification
	public void addBookByBehavior(Book book) {
		if (book.getPrice() <= 500){
			return;
		}
		bookRepository.save(book);
	}

	public void addBookByBehaviorWithGreaterPrice(BookRequest bookRequest) {
		if (bookRequest.getPrice() <= 500){
			return;
		}
		Book book =
				new Book(null, bookRequest.getTitle(), bookRequest.getPrice(), bookRequest.getPublishedDate());
		bookRepository.save(book);
	}

	public void updatePrice(Long bookId, int updatePrice){
		if (bookId == null){
			return;
		}
		Book book = bookRepository.findBookById(bookId);
		if (book.getPrice() == updatePrice){
			return;
		}
		book.setPrice(updatePrice);
		bookRepository.save(book);
	}

	public void updatePriceNoSamePriceIF(Long bookId, int updatePrice){
		if (bookId == null){
			return;
		}
		Book book = bookRepository.findBookById(bookId);
		book.setPrice(updatePrice);
		bookRepository.save(book);
	}

}
