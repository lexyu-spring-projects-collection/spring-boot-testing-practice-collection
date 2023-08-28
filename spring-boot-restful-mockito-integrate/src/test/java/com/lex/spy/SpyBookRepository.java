package com.lex.spy;

import com.lex.entity.Book;
import com.lex.repository.BookRepository;

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
}
