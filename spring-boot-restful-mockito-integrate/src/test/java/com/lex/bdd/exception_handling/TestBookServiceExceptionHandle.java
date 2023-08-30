package com.lex.bdd.exception_handling;

import com.lex.entity.Book;
import com.lex.exception.DatabaseReadException;
import com.lex.exception.DatabaseWriteException;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author : Lex Yu
 * @date : 2023/8/30
 */
@ExtendWith(MockitoExtension.class)
public class TestBookServiceExceptionHandle {
	@Mock
	private BookRepository bookRepository;
	@InjectMocks
	private BookService bookService;

	@Test
	public void testTotalPriceOfBooksTraditionalMockito() throws SQLException {
		Mockito.when(bookRepository.findAllBooks()).thenThrow(SQLException.class);
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
	}

	@Test
	public void testTotalPriceOfBooksBDDMockito() throws SQLException {
		// Given
		BDDMockito.given(bookRepository.findAllBooks()).willThrow(SQLException.class);

		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
	}

}
