package com.lex.dummy;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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

	@Test
	public void testDummyWithMockito(){
		BookRepository bookRepository = Mockito.mock(BookRepository.class);
		DummyEmailService emailService = Mockito.mock(DummyEmailService.class);
		BookService bookService = new BookService(bookRepository, emailService);

		Book book1 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book(2L, "Junit 5 In Action", 400, LocalDate.now());
		Book book3 = new Book(3L, "AssertJ In Action", 300, LocalDate.now());
		Book book4 = new Book(4L, "Spring Boot Test In Action", 200, LocalDate.now());
		Book book5 = new Book(5L, "Hamcrest In Action", 100, LocalDate.now());

		Collection<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);

		Mockito.when(bookRepository.findAll()).thenReturn(books);

		assertEquals(5, bookService.findNumberOfBooks());
	}
}
