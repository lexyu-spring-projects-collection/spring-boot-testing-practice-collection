package com.lex.practice.service;

import com.lex.practice.entity.Movie;
import com.lex.practice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Lex Yu
 * @date : 2023/8/31
 */
@Service
public class MovieService {
	private final MovieRepository movieRepository;


	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	public Movie getMovieById(Long id) {
		return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie found for the id "+id));
	}

	public Movie updateMovie(Movie movie, Long id) {
		Movie existingMovie = movieRepository.findById(id).get();
		existingMovie.setGenera(movie.getGenera());
		existingMovie.setName(movie.getName());
		existingMovie.setReleaseDate(movie.getReleaseDate());
		return movieRepository.save(existingMovie);
	}

	public void deleteMovie(Long id) {
		Movie existingMovie = movieRepository.findById(id).get();
		movieRepository.delete(existingMovie);

	}
}
