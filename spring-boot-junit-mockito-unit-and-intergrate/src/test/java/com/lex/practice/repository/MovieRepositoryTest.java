package com.lex.practice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Lex Yu
 * @date : 2023/8/31
 */
@DataJpaTest
class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Test
	void saveMovie(){
		// Given

		// When

		// Then
	}

	@Test
	void findByGenera() {

	}
}