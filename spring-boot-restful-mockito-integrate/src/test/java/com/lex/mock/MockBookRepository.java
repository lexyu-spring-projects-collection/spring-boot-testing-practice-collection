package com.lex.mock;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
class MockBookRepository implements BookRepository {
	// Test Doubles without Mockito, Mock
	// Give Capability to record the every interaction made with save() method
	int saveCalled = 0;
	Book lastAddedBook = null;
	@Override
	public void save(Book book) {
		saveCalled++;
		lastAddedBook = book;
	}

	public void  verify(Book book, int times){
		assertEquals(times, saveCalled);
		assertEquals(book, lastAddedBook);
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
}