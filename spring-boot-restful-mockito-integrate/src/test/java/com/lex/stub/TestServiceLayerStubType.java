
package com.lex.stub;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;

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
}
