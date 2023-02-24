package com.lex.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.practice.dao.BookRepository;
import com.lex.practice.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/2/23 下午 02:06
 */
//@RunWith(MockitoJUnitRunner.class) //JUnit 4, enable Mockito annotation
@ExtendWith(MockitoExtension.class) // JUnit 5, equivalent above
class BookControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private AutoCloseable closeable;
    @InjectMocks
    private BookController bookController;
    @Mock
    BookRepository bookRepository;

    List<Book> books = List.of(
            new Book(1L, "A Book", "A summary", 5),
            new Book(2L, "B Book", "B summary", 4),
            new Book(3L, "C Book", "C summary", 3)
    );

    @BeforeEach // JUnit 5
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
        bookController = new BookController(bookRepository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getAllBookRecords() throws Exception {
        when(bookRepository.findAll()).thenReturn(books);
        mockMvc.perform(get("/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("C Book")))
                .andReturn();
    }

    @Test
    void getBookById() {
    }

    @Test
    void createBookRecord() {
    }

    @Test
    void updateBookRecord() {
    }

    @AfterEach
    void closeRepository() throws Exception {
        closeable.close();
    }
}