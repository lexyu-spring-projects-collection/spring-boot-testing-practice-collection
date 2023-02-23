package com.lex.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.practice.dao.BookRepository;
import com.lex.practice.entity.Book;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/2/23 下午 02:06
 */
@RunWith(MockitoJUnitRunner.class) // JUnit4
class BookControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    List<Book> books = List.of(
            new Book(1L, "A Book", "A summary", 5),
            new Book(2L, "B Book", "B summary", 4),
            new Book(3L, "C Book", "C summary", 3)
    );
    Book RECORD_1 = new Book(1L, "A Book", "A summary", 5);
    Book RECORD_2 = new Book(2L, "B Book", "B summary", 4);
    Book RECORD_3 = new Book(3L, "C Book", "C summary", 3);


    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getAllBookRecords() throws Exception {
        List<Book> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
        when(bookRepository.findAll()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].nane", is("C Book")));
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
}