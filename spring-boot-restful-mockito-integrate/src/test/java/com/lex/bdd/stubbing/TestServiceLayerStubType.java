
package com.lex.bdd.stubbing;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import com.lex.stub.StubBookRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
public class TestServiceLayerStubType {

	@Mock
	private BookRepository bookRepository;
	@InjectMocks
	private BookService bookService;

	@Test
	public void testStubbingTraditionalMockitoStyle() {
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

	@Test
	@DisplayName("Given - New books, When - Get new books with applied discount method is called, Then - New books with applied discount is returned")
	public void test_Given_NewBooks_When_GetNewBooksWithAppliedDiscountIsCalled_Then_NewBooksWithAppliedDiscountIsReturned () {
		// Arrange - Given
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

		BDDMockito.given(bookRepository.findNewBooks(7)).willReturn(books);

		// Act - When
		List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);
		newBooksWithAppliedDiscount.forEach(System.out::println);

		// Assert - Then
//		assertEquals(5, newBooksWithAppliedDiscount.size());
//		assertEquals(450, newBooksWithAppliedDiscount.get(0).getPrice());
//		assertEquals(360, newBooksWithAppliedDiscount.get(1).getPrice());

		// AssertJ - BDDAssertions
		BDDAssertions.then(newBooksWithAppliedDiscount).isNotNull();
		BDDAssertions.then(newBooksWithAppliedDiscount.size()).isEqualTo(5);
		BDDAssertions.then(newBooksWithAppliedDiscount.get(0).getPrice()).isEqualTo(450);
		BDDAssertions.then(newBooksWithAppliedDiscount.get(1).getPrice()).isEqualTo(360);
	}
}
