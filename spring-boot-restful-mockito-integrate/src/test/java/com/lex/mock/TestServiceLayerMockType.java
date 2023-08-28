
package com.lex.mock;

import com.lex.entity.Book;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public class TestServiceLayerMockType {

	@Test
	public void testMock() {
		MockBookRepository mockBookRepository = new MockBookRepository();
		BookService bookService = new BookService(mockBookRepository);

		Book book1 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book(2L, "Junit 5 In Action", 400, LocalDate.now());
		Book book3 = new Book(3L, "AssertJ In Action", 300, LocalDate.now());
		Book book4 = new Book(4L, "Spring Boot Test In Action", 200, LocalDate.now());
		Book book5 = new Book(5L, "Hamcrest In Action", 100, LocalDate.now());

		bookService.addBookBySpy(book1); // return
		bookService.addBookBySpy(book2); // save will be called
		bookService.addBookBySpy(book3);
		bookService.addBookBySpy(book4);
		bookService.addBookBySpy(book5);

		mockBookRepository.verify(book5, 4);
	}
}
