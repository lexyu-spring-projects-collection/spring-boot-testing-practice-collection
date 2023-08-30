package com.lex.repository;

import com.lex.entity.Book;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public interface BookRepository {
	void save(Book book);
	Collection<Book> findAll();
	List<Book> findNewBooks(int days);
	Book findBookById(Long bookId);
	List<Book> findAllBooks() throws SQLException;
	void save_v2(Book book) throws SQLException;
	Book findBookByTitleAndPulishedDate(String title, LocalDate localDate);

	Book findBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital);

	void saveAll(List<Book> books);
}
