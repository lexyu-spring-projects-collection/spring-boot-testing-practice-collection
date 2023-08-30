package com.lex.spies;

import com.lex.entity.Book;
import com.lex.service.BookManager;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Lex Yu
 * @date : 2023/8/30
 */
@ExtendWith(MockitoExtension.class)
public class TestBookManager {
	@Spy
	private BookService bookService;
	@InjectMocks
	private BookManager bookManager;

	@Test
	public void testMockitoSpy() {
		// Without Annoation
//		BookService bookService = Mockito.spy(BookService.class);
//		BookManager bookManager = new BookManager(bookService);

		// We need to mock findBook
		// because it is communicating to database or not implemented
		// We need to call getAppliedDiscount to calculate the discounted price
		Book book = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Mockito.doReturn(book).when(bookService).findBook(1L);
//		Mockito.when(bookService.findBook(1L)).thenReturn(book); // This way will call actual method, throw err
		int actualPriceAfterDiscounted = bookManager.appliedDiscountOnBook(1L, 10);

		assertEquals(450, actualPriceAfterDiscounted);
	}
}
