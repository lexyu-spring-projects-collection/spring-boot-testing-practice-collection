package com.lex.practice.repository;

import com.lex.practice.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author : Lex Yu
 * @date : 2023/9/1
 */
@Slf4j
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	private Book a_book;

	@BeforeEach
	void setup() {
		a_book = Book.builder()
				.id(1L)
				.name("A")
				.summary("A SUMMARY")
				.rating(10)
				.build();
	}

	@Test
	@Sql(statements = "INSERT INTO BOOKS(id, name, summary, rating) VALUES (1, 'A', 'A SUMMARY', 10)",
			executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	void getAllBooks() {
		List<Book> bookList = bookRepository.findAll();
		log.info("{}", bookList);
		assertThat(bookList).isNotNull().hasSize(1);
		assertThat(bookList.get(0)).isEqualTo(a_book);
	}

}
