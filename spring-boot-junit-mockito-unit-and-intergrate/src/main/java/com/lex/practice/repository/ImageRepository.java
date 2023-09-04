package com.lex.practice.repository;

import com.lex.practice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Lex Yu
 * @date : 2023/9/4
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	Optional<Image> findByName(String name);
}
