package com.lex.dummy;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public class TestServiceLayerDummyType {

	@Test
	public void testDummy() {
		BookRepository bookRepository = new DummyBookRepository();
		DummyEmailService emailService = new DummyEmailService();
		BookService bookService = new BookService(bookRepository, emailService);

		bookService.addBook(new Book(1L, "Mockito In Action", 500, LocalDate.now()));
		bookService.addBook(new Book(2L, "Junit 5 In Action", 400, LocalDate.now()));
		bookService.addBook(new Book(3L, "AssertJ In Action", 300, LocalDate.now()));
		bookService.addBook(new Book(4L, "Spring Boot Test In Action", 200, LocalDate.now()));
		bookService.addBook(new Book(5L, "Hamcrest In Action", 100, LocalDate.now()));

		assertEquals(5, bookService.findNumberOfBooks());
	}
}
