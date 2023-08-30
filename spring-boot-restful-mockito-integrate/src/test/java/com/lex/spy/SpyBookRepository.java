package com.lex.spy;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
public class SpyBookRepository implements BookRepository {
	// Give Capability to record the every interaction made with save() method
	int saveCalled = 0;
	Book lastAddedBook = null;
	@Override
	public void save(Book book) {
		saveCalled++;
		lastAddedBook = book;
	}

	public int timesCalled(){
		return saveCalled;
	}

	public boolean calledWith(Book book){
		return lastAddedBook.equals(book);
	}

//	------------------------------------------------------------------------------
	@Override
	public Collection<Book> findAll() {
		return null;
	}

	@Override
	public List<Book> findNewBooks(int days) {
		return null;
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
