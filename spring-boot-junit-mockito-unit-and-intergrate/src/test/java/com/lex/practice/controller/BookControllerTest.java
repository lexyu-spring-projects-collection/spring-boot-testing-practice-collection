package com.lex.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import org.springframework.test.util.ExceptionCollector;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer();
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
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(bookController)
                .setControllerAdvice(new ExceptionCollector())
                .build();
    }

    @Test
    void getAllBookRecords() throws Exception {
        when(bookRepository.findAll()).thenReturn(books);

        mockMvc.perform(get("/book").contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("C Book")))
                .andReturn();
    }

    @Test
    void getBookById() throws Exception {
        given(bookRepository.findById(books.get(0).getId()))
                .willReturn(Optional.ofNullable(books.get(0)));

        String str = mockMvc.perform(get("/book/1").contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("A Book")))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Book=" + str);
    }

    @Test
    void createBookRecord() throws Exception {
        Book record = Book
                .builder()
                .id(4L)
                .name("Introduction to Java")
                .summary("SUMMARY")
                .rating(5)
                .build();

        given(bookRepository.save(record))
                .willReturn(record);

        String content = mapper.writeValueAsString(record);

        mockMvc.perform(post("/book")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Introduction to Java")))
                .andDo(print());
    }

    @Test
    void updateBookRecord() throws Exception {
        Book updatedRecord = Book.builder()
                .id(1L)
                .name("Updated Book Name")
                .summary("Updated Summary")
                .rating(1)
                .build();

        given(bookRepository.findById(books.get(0).getId())).willReturn(Optional.ofNullable(books.get(0)));
//        given(bookRepository.save(updatedRecord)).willReturn(updatedRecord);

        String updatedContent = writer.writeValueAsString(updatedRecord);

        mockMvc.perform(put("/book")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE)
                        .content(updatedContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Updated Book Name")))
                .andDo(print());
    }

    @Test
    public void deleteBookById() throws Exception {
        given(bookRepository.findById(books.get(0).getId()))
                .willReturn(Optional.ofNullable(books.get(0)));

        mockMvc.perform(delete("/book/1")
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }


    @AfterEach
    void closeRepository() throws Exception {
        closeable.close();
    }
}