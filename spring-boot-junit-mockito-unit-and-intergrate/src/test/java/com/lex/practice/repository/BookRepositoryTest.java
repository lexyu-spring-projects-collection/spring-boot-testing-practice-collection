package com.lex.practice.repository;

import com.lex.practice.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author : Lex Yu
 * @date : 2023/9/1
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	// FIXME: Path Issue
	@Test
	@Sql("createBooks.sql")
	void getAllBooks(){
		List<Book> all = bookRepository.findAll();
		log.info("{}",all);
	}
}
