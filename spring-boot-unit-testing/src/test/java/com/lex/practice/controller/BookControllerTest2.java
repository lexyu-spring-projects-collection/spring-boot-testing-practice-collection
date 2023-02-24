package com.lex.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.practice.dao.BookRepository;
import com.lex.practice.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/2/23 下午 02:06
 * Ref Spring Boot Testing Doc
 * `@WebMvcTest + @MockBean`
 */
//@DataJpaTest // Test Persistence Layer
@WebMvcTest(BookController.class) // Test slice of an application
class BookControllerTest2 {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookRepository bookRepository;

    List<Book> books = List.of(
            new Book(1L, "A Book", "A summary", 5),
            new Book(2L, "B Book", "B summary", 4),
            new Book(3L, "C Book", "C summary", 3)
    );

    @Test
    void getAllBookRecords() throws Exception {
        given(bookRepository.findAll()).willReturn(books);
        mockMvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("C Book")))
                .andReturn();
    }

    @Test
    void getBookById() throws Exception {
        given(bookRepository.findById(books.get(0).getId()))
                .willReturn(Optional.ofNullable(books.get(0)));
        mockMvc.perform(get("/book/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("A Book")))
                .andReturn();
    }

    @Test
    void createBookRecord() {
    }

    @Test
    void updateBookRecord() {
    }
}