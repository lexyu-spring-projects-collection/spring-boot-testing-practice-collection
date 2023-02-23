package com.lex.practice.controller;

import com.lex.practice.dao.BookRepository;
import com.lex.practice.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/2/23 下午 12:12
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBookRecords() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }

    @PostMapping
    public Book createBookRecord(@RequestBody @Valid Book bookRecord){
        return bookRepository.save(bookRecord);
    }

    //TODO: Write /delete method endpoint using TDD method

    @PutMapping
    public Book updateBookRecord(@RequestBody @Valid Book bookRecord){
        if (bookRecord == null || bookRecord.getId() == null) {
            throw new NoSuchElementException("Not Found");
        }
        Optional<Book> optionalBook = bookRepository.findById(bookRecord.getId());
        return optionalBook.orElseThrow();
    }

}
