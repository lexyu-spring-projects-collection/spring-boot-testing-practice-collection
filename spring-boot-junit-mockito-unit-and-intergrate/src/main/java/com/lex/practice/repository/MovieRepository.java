package com.lex.practice.repository;

import com.lex.practice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Lex Yu
 * @date : 2023/8/31
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByGenera(String genera);
}
