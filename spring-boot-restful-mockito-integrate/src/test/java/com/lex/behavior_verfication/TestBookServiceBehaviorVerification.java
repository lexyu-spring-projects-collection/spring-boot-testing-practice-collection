package com.lex.behavior_verfication;

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

/**
 * @author : Lex Yu
 * @date : 2023/8/29
 */
@ExtendWith(MockitoExtension.class)
public class TestBookServiceBehaviorVerification {

	@Mock
	private BookRepository bookRepository;
	@InjectMocks
	private BookService bookService;

	@Test
	public void testAddBook() {
		Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());

		bookService.addBook(book);
//		bookService.addBookByBehavior(book);

		Mockito.verify(bookRepository).save(book);

	}

	// verify an interaction
	@Test
	public void testSaveBookWithBookRequestWithGreaterPrice() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
		Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
		bookService.addBookByBehaviorWithGreaterPrice(bookRequest);
		Mockito.verify(bookRepository, Mockito.times(0)).save(book);
	}

	@Test
	public void testSaveBookWithBookRequestWithGreaterPriceTimes() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
		Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
		bookService.addBookByBehaviorWithGreaterPrice(bookRequest);
		bookService.addBookByBehaviorWithGreaterPrice(bookRequest);
		Mockito.verify(bookRepository, Mockito.times(2)).save(book);
	}

	/*
	@Test
	public void testSaveBookWithBookRequestWithGreaterPriceArgsDiffException() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
		Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
		bookService.addBookByBehaviorWithGreaterPrice(bookRequest);
		Mockito.verify(bookRepository, Mockito.times(1)).save(book);
	}
	 */

	@Test
	public void testSaveBookWithBookRequestWithGreaterPriceNever() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
		Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
		bookService.addBookByBehaviorWithGreaterPrice(bookRequest);
		Mockito.verify(bookRepository, Mockito.never()).save(book);
	}

	@Test
	public void testUpdatePriceNull() {
		bookService.updatePrice(null, 600);
		Mockito.verifyNoInteractions(bookRepository);
	}

	@Test
	public void testUpdatePriceSame() {
		Book book = new Book(1L, "Mockito In Action", 600, LocalDate.now());
		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book);
		bookService.updatePrice(1L, 600);
		Mockito.verify(bookRepository).findBookById(1L);
		Mockito.verifyNoMoreInteractions(bookRepository);
	}

	@Test
	public void testUpdatePriceVerifyNoMore() {
		Book book = new Book(1L, "Mockito In Action", 600, LocalDate.now());
		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book);
		bookService.updatePriceNoSamePriceIF(1L, 600);
		Mockito.verify(bookRepository).findBookById(1L);
		Mockito.verify(bookRepository).save(book);
		Mockito.verifyNoMoreInteractions(bookRepository);
	}

	@Test
	public void testUpdatePriceVerifyOrder() {
		Book book = new Book(1L, "Mockito In Action", 600, LocalDate.now());
		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book);
		bookService.updatePriceNoSamePriceIF(1L, 600);

		InOrder inOrder = Mockito.inOrder(bookRepository);

		inOrder.verify(bookRepository).findBookById(1L);
		inOrder.verify(bookRepository).save(book);
	}


}
