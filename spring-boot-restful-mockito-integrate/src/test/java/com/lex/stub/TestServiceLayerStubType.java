
package com.lex.stub;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public class TestServiceLayerStubType {

	@Test
	public void testStub() {
		BookRepository bookRepository = new StubBookRepository();
		BookService bookService = new BookService(bookRepository);

		List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

		newBooksWithAppliedDiscount.forEach(System.out::println);

		assertEquals(5, newBooksWithAppliedDiscount.size());
		assertEquals(450, newBooksWithAppliedDiscount.get(0).getPrice());
		assertEquals(360, newBooksWithAppliedDiscount.get(1).getPrice());
	}

	@Test
	public void testStubWithMockito() {
		BookRepository bookRepository = Mockito.mock(BookRepository.class);
		BookService bookService = new BookService(bookRepository);

		Book book1 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book(2L, "Junit 5 In Action", 400, LocalDate.now());
		Book book3 = new Book(3L, "AssertJ In Action", 300, LocalDate.now());
		Book book4 = new Book(4L, "Spring Boot Test In Action", 200, LocalDate.now());
		Book book5 = new Book(5L, "Hamcrest In Action", 100, LocalDate.now());

		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);

		Mockito.when(bookRepository.findNewBooks(7)).thenReturn(books);

		List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

		newBooksWithAppliedDiscount.forEach(System.out::println);

		assertEquals(5, newBooksWithAppliedDiscount.size());
		assertEquals(450, newBooksWithAppliedDiscount.get(0).getPrice());
		assertEquals(360, newBooksWithAppliedDiscount.get(1).getPrice());
	}
}
