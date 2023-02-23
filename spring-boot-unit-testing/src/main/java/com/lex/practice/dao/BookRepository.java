package com.lex.practice.dao;

import com.lex.practice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/2/23 下午 12:11
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
