package com.lex.argument_matchers;

import com.lex.entity.Book;
import com.lex.entity.BookRequest;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Lex Yu
 */
@ExtendWith(MockitoExtension.class)
public class TestBookServiceBehaviorVerification {

	@Mock
	private BookRepository bookRepository;
	@InjectMocks
	private BookService bookService;

	@Test
	public void testUpdatePriceVerifyNoMore() {
		Book book1 = new Book(1L, "Mockito In Action", 600, LocalDate.now());
		Book book2 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Mockito.when(bookRepository.findBookById(Mockito.any(Long.class))).thenReturn(book1);
		bookService.updatePriceForBDD(1L, 500);
		Mockito.verify(bookRepository).save(book2);
	}

	// Argument Matchers
	// should be provided for all arguments
	// can't be used outside stubbing / verification
	/*
	@Test
	public void testInvalidUseOfArgumentMatchers(){
		Book book = new Book(1L, "Mockito In Action", 600, LocalDate.now());
		Mockito.when(bookRepository.findBookByTitleAndPulishedDate(Mockito.any(), Mockito.any(LocalDate.class)))
				.thenReturn(book);
		Book actualBook = bookService.getBookByTitleAndPublishedDate(Mockito.eq("Mockito In Action"), Mockito.any());
		assertEquals("Mockito In Action", actualBook.getTitle());
	}
	 */

	@Test
	public void testSpecificTypeOfArgumentMatchers() {
		Book book = new Book(1L, "Mockito In Action", 600, LocalDate.now(), true);

//		Mockito.when(bookRepository
//						.findBookByTitleAndPriceAndIsDigital(Mockito.anyString(), Mockito.anyInt(), Mockito.anyBoolean()))
//				.thenReturn(book);

		Mockito.when(bookRepository
						.findBookByTitleAndPriceAndIsDigital(Mockito.anyString(), Mockito.anyInt(), Mockito.anyBoolean()))
				.thenReturn(book);

		Book actualBook =
				bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, false);
		System.out.println(actualBook);
		assertEquals("Mockito In Action", actualBook.getTitle());

		assertEquals(book, actualBook);
	}

	@Test
	public void testCollectionTypeArgumentMatchers() {
		List<Book> books = new ArrayList<>();
		Book book1 = new Book(1L, "Mockito In Action", 600, LocalDate.now());
		Book book2 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		books.add(book1);
		books.add(book2);
		bookService.addBooks(books);
		Mockito.verify(bookRepository).saveAll(Mockito.anyList());
	}

	@Test
	public void testStringTypeOfArgumentMatchers() {
		Book book = new Book(1L, "Mockito In Action", 600, LocalDate.now());

		Mockito.when(bookRepository
						.findBookByTitleAndPriceAndIsDigital
								(Mockito.startsWith("Mockito"), Mockito.anyInt(), Mockito.anyBoolean()))
				.thenReturn(book);

		Book actualBook =
				bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, false);
		System.out.println(actualBook);
		assertEquals("Mockito In Action", actualBook.getTitle());

		assertEquals(book, actualBook);
	}
}

