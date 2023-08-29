package com.lex.annotation_support;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Lex Yu
 * @date : 2023/8/29
 */
//@RunWith(MockitoJUnitRunner.class) // JUnit 4
@ExtendWith(MockitoExtension.class) // JUnit 5
public class AnnotationsTest {

	/* JUnit 4
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	 */

	@Mock
	private BookRepository bookRepository;
	@InjectMocks
	private BookService bookService;

	/* JUnit4
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	 */

	@Test
	public void demoCreateMocksUsingAnnotations(){

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
