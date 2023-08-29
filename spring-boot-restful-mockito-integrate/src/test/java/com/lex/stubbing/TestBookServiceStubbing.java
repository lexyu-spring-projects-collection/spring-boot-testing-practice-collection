package com.lex.stubbing;

import com.lex.entity.Book;
import com.lex.entity.BookRequest;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
 * @date : 2023/8/29
 */
@ExtendWith(MockitoExtension.class)
public class TestBookServiceStubbing {

	@Mock
	private BookRepository bookRepository;
	@InjectMocks
	private BookService bookService;

	@Test
	public void testCalculateTotalCostOfBooks() {
		Book book1 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book(2L, "Junit 5 In Action", 400, LocalDate.now());
		Book book3 = new Book(3L, "AssertJ In Action", 300, LocalDate.now());
		Book book4 = new Book(4L, "Spring Boot Test In Action", 200, LocalDate.now());
		Book book5 = new Book(5L, "Hamcrest In Action", 100, LocalDate.now());

		List<Long> bookIds = new ArrayList<>();
		bookIds.add(1L);
//		bookIds.add(2L);
//		bookIds.add(3L);
//		bookIds.add(4L);
//		bookIds.add(5L);

		bookIds.add(1L);
		bookIds.add(1L);
		bookIds.add(1L);
		bookIds.add(1L);

		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book1, book1, book1, book1, book1);
//		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book1);
//		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book1);
//		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book1);
//		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book1);

//		Mockito.when(bookRepository.findBookById(1L)).thenReturn(book1);
//		Mockito.when(bookRepository.findBookById(2L)).thenReturn(book2);
//		Mockito.when(bookRepository.findBookById(3L)).thenReturn(book3);
//		Mockito.when(bookRepository.findBookById(4L)).thenReturn(book4);
//		Mockito.when(bookRepository.findBookById(5L)).thenReturn(book5);

		// UnfinishedStubbingException
//		Mockito.doReturn(book1).when(bookRepository.findBookById(1L));
//		Mockito.doReturn(book2).when(bookRepository.findBookById(2L));
//		Mockito.doReturn(book3).when(bookRepository.findBookById(3L));
//		Mockito.doReturn(book4).when(bookRepository.findBookById(4L));
//		Mockito.doReturn(book5).when(bookRepository.findBookById(5L));

//		Mockito.doReturn(book1).when(bookRepository).findBookById(1L);
//		Mockito.doReturn(book2).when(bookRepository).findBookById(2L);
//		Mockito.doReturn(book3).when(bookRepository).findBookById(3L);
//		Mockito.doReturn(book4).when(bookRepository).findBookById(4L);
//		Mockito.doReturn(book5).when(bookRepository).findBookById(5L);

		int actualCost = bookService.calculateTotalCost(bookIds);
		System.out.println(actualCost);

		assertEquals(2500, actualCost);
//		assertEquals(1500, actualCost);
	}

	@Test
	public void testSaveBook() {
		Book book1 = new Book(null, "Mockito In Action", 500, LocalDate.now());

		Mockito.doNothing().when(bookService).addBookByStubbing(book1);

		bookService.addBookByStubbing(book1);
	}

	@Test
	public void testSaveBookWithBookRequest() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
		Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());

		Mockito.doNothing().when(bookRepository).save(book);

		bookService.addBookByStubbing(bookRequest);
	}


	@Test
	public void testSaveBookWithBookRequestWithGreaterPrice() {
		BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
		Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());

//		Mockito.doNothing().when(bookRepository).save(book);

		bookService.addBookByStubbingWithGreaterPrice(bookRequest);
	}
}
