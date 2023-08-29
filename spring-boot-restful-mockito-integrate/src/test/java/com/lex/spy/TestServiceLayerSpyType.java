package com.lex.spy;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public class TestServiceLayerSpyType {

	@Test
	public void testSpy() {
		SpyBookRepository bookRepositorySpy = new SpyBookRepository();
		BookService bookService = new BookService(bookRepositorySpy);

		Book book1 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book(2L, "Junit 5 In Action", 400, LocalDate.now());
		Book book3 = new Book(3L, "AssertJ In Action", 300, LocalDate.now());
		Book book4 = new Book(4L, "Spring Boot Test In Action", 200, LocalDate.now());
		Book book5 = new Book(5L, "Hamcrest In Action", 100, LocalDate.now());

		/*
		bookService.addBook(book1);
		bookService.addBook(book2);
		bookService.addBook(book3);
		bookService.addBook(book4);
		bookService.addBook(book5);

		assertEquals(5, bookRepositorySpy.timesCalled());
		assertTrue(bookRepositorySpy.calledWith(book5));
		 */

		bookService.addBookBySpy(book1);
		assertEquals(0, bookRepositorySpy.timesCalled());

		bookService.addBookBySpy(book2);
		bookService.addBookBySpy(book3);
		bookService.addBookBySpy(book4);
		bookService.addBookBySpy(book5);

		assertEquals(4, bookRepositorySpy.timesCalled());
		assertTrue(bookRepositorySpy.calledWith(book5));
	}

	@Test
	public void testSpyWithMockito() {
		BookRepository bookRepositorySpy = Mockito.spy(BookRepository.class);
		BookService bookService = new BookService(bookRepositorySpy);

		Book book1 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book(2L, "Junit 5 In Action", 400, LocalDate.now());
		Book book3 = new Book(3L, "AssertJ In Action", 300, LocalDate.now());
		Book book4 = new Book(4L, "Spring Boot Test In Action", 200, LocalDate.now());
		Book book5 = new Book(5L, "Hamcrest In Action", 100, LocalDate.now());

		bookService.addBookBySpy(book1);
		bookService.addBookBySpy(book2);
		bookService.addBookBySpy(book3);
		bookService.addBookBySpy(book4);
		bookService.addBookBySpy(book5);


		Mockito.verify(bookRepositorySpy, Mockito.times(1)).save(book5);
		Mockito.verify(bookRepositorySpy, Mockito.times(0)).save(book1);

	}
}
