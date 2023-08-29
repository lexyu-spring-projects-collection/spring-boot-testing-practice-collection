package com.lex.repository;

import com.lex.entity.Book;

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

}
