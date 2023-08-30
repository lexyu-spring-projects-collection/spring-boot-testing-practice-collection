package com.lex.stub;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public class StubBookRepository implements BookRepository {
	@Override
	public void save(Book book) {

	}

	@Override
	public Collection<Book> findAll() {
		return null;
	}
//	------------------------------------------------------------------------------

	// Test Doubles without Mockito, Stub
	@Override
	public List<Book> findNewBooks(int days) {
		List<Book> books = new ArrayList<>();
		Book book1 = new Book(1L, "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book(2L, "Junit 5 In Action", 400, LocalDate.now());
		Book book3 = new Book(3L, "AssertJ In Action", 300, LocalDate.now());
		Book book4 = new Book(4L, "Spring Boot Test In Action", 200, LocalDate.now());
		Book book5 = new Book(5L, "Hamcrest In Action", 100, LocalDate.now());
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);

		return books;
	}

	@Override
	public Book findBookById(Long bookId) {
		return null;
	}

	@Override
	public List<Book> findAllBooks() throws SQLException {
		return null;
	}

	@Override
	public void save_v2(Book book) throws SQLException {

	}

	@Override
	public Book findBookByTitleAndPulishedDate(String title, LocalDate localDate) {
		return null;
	}

	@Override
	public Book findBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital) {
		return null;
	}

	@Override
	public void saveAll(List<Book> books) {

	}
}
