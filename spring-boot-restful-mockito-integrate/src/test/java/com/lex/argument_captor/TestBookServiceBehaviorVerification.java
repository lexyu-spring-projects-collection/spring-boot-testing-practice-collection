package com.lex.argument_captor;

import com.lex.entity.Book;
import com.lex.entity.BookRequest;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	@Captor
	private ArgumentCaptor<Book> bookArgumentCaptor;

	@Test
	public void testAddBookWithoutAnnotation() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
		ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
		bookService.addBook(bookRequest);
		Mockito.verify(bookRepository).save(bookArgumentCaptor.capture());
		Book book = bookArgumentCaptor.getValue();
		System.out.println(bookArgumentCaptor.getAllValues());
		System.out.println(book);
		assertEquals("Mockito In Action", book.getTitle());
	}

	@Test
	public void testAddBookWithAnnotation() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
		bookService.addBook(bookRequest);
		Mockito.verify(bookRepository).save(bookArgumentCaptor.capture());
		Book book = bookArgumentCaptor.getValue();
		System.out.println(bookArgumentCaptor.getAllValues());
		System.out.println(book);
		assertEquals("Mockito In Action", book.getTitle());
	}
}
