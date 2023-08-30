package com.lex.exception_handling;

import com.lex.entity.Book;
import com.lex.exception.DatabaseReadException;
import com.lex.exception.DatabaseWriteException;
import com.lex.repository.BookRepository;
import com.lex.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
	public void testTotalPriceOfBooks() throws SQLException {
		Mockito.when(bookRepository.findAllBooks()).thenThrow(SQLException.class);
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
//		int totalPricesOfBooks = bookService.getTotalPriceOfBooks();
	}

	@Test
	public void testTotalPriceOfBooks_v2() throws SQLException {
		Mockito.when(bookRepository.findAllBooks()).thenThrow(new SQLException("Database no available"));
		assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
	}

	@Test
	public void testAddBook() throws SQLException {
		Book book = new Book(1L, "Mockito In Action", 500, LocalDate.now());

		Mockito.doThrow(SQLException.class).when(bookRepository).save_v2(book);

		assertThrows(DatabaseWriteException.class, () -> bookService.addBook_v2(book));
	}


}
