package com.lex.dummy;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
class DummyBookRepository implements BookRepository {
	// Test Doubles without Mockito, Dummy
	// In memory database, HashMap or List
	Map<Long, Book> bookStore = new HashMap<>();

	@Override
	public void save(Book book) {
		bookStore.put(book.getBookId(), book);
	}

	@Override
	public Collection<Book> findAll() {
		return bookStore.values();
	}

	// -----------------------------------------------------------------------------------------------
	@Override
	public List<Book> findNewBooks(int days) {
		return null;
	}

	@Override
	public Book findBookById(Long bookId) {
		return null;
	}
}