package com.lex.bdd.behavior_verification;

import com.lex.entity.Book;
import com.lex.entity.BookRequest;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

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
		Book book = new Book(1L, "Mockito In Action", 600, LocalDate.now());
		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book);
		bookService.updatePriceNoSamePriceIF(1L, 600);
		Mockito.verify(bookRepository).findBookById(1L);
		Mockito.verify(bookRepository).save(book);
		Mockito.verifyNoMoreInteractions(bookRepository);
	}

	@Test
	public void test_Given_ABook_When_UpdatePriceIsCalledWithNewPrice_Then_BookPriceIsUpdated(){
		// Given
		Book book = new Book(1L, "Mockito In Action", 600, LocalDate.now());
		BDDMockito.given(bookRepository.findBookById(1L)).willReturn(book);

		// When
		bookService.updatePriceForBDD(1L, 600);

		// Then
		BDDMockito.then(bookRepository).should().save(book);
	}
}
