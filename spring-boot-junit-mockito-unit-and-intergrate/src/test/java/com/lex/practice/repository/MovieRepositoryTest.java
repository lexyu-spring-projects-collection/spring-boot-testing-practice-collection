package com.lex.practice.repository;

import com.lex.practice.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.activation.DataSource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Lex Yu
 * @date : 2023/8/31
 */
@Slf4j
@DataJpaTest
class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;

	// Given
	private final Movie aMovie =
			new Movie("Avatar", "Action", LocalDate.of(2000, Month.APRIL, 22));
	private final List<Movie> movies = new ArrayList<>();

	@BeforeEach
	void init(){
		// Given
		Movie bMovie = new Movie("Bvatar", "Action", LocalDate.of(2000, Month.FEBRUARY, 2));
		Movie cMovie = new Movie("Cvatar", "Action", LocalDate.of(2000, Month.DECEMBER, 12));
		movies.add(aMovie);
		movies.add(bMovie);
		movies.add(cMovie);
	}

	@Test
	void injectedComponentsAreNotNull(){
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(movieRepository).isNotNull();
	}

	@Test
//	@Commit
//	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@DisplayName("It should save the movie to the database")
	void saveMovie(){
		// Given = Arrange
//		Movie aMovie = new Movie("Avatar", "Action", LocalDate.of(2000, Month.APRIL, 22));

		// When = Act
		Movie savedMovie = movieRepository.save(aMovie);

		// Then = Assert
		assertNotNull(savedMovie);
		assertThat(savedMovie.getId()).isNotEqualTo(null);
	}

	@Test
	@DisplayName("It should return the movies list with size of 3")
	void getAllMovies() {
		// When
		List<Movie> movieList = movieRepository.findAll();

		// Then
		assertNotNull(movieList);
		assertThat(movieList).isNotNull();
		assertEquals(3, movies.size());
	}


	@Test
	@DisplayName("It should return movie by specified Id")
	void getMovieById() {
		// When
		Movie movie = movieRepository.findById(1L).orElseThrow();

		// Then
		assertNotNull(movie);
		assertThat(movie).isNotNull();
	}
}