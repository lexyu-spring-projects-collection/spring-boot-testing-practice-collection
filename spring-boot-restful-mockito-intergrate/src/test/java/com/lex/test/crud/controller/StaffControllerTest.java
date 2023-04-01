package com.lex.test.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.test.crud.entity.UserInfo;
import com.lex.test.crud.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StaffService staffService;

    @Autowired
    private ObjectMapper objectMapper;

    // JUnit test for Create UserInfo REST API
    @Test
    void createUserInfo() throws Exception {
        // given - precondition or setup
        UserInfo userInfo = UserInfo.builder()
                .firstname("Test")
                .lastname("test")
                .username("test@gmail.com")
                .password("123456")
                .build();
        given(staffService.save(any(UserInfo.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);
        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/staffs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userInfo)));
        // then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id",is(userInfo.getId())))
                .andExpect(jsonPath("$.firstname",is(userInfo.getFirstname())))
                .andExpect(jsonPath("$.lastname",is(userInfo.getLastname())))
                .andExpect(jsonPath("$.username",is(userInfo.getUsername())))
                .andExpect(jsonPath("$.password",is(userInfo.getPassword())));

    }

    // JUnit test for Get All UserInfo REST API
    @Test
    void getAllUserInfo() throws Exception {
        // given - precondition or setup
        List<UserInfo> listOfUsers = new ArrayList<>();
        listOfUsers.add(UserInfo.builder().id(1).firstname("L").lastname("Y")
                .username("Lex@gmail.com").password("123456").build());
        listOfUsers.add(UserInfo.builder().id(2).firstname("Hal").lastname("Jordan")
                .username("Lantern@gmail.com").password("123456").build());
        given(staffService.findAll()).willReturn(listOfUsers);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/staffs"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(listOfUsers.size())));
    }

    // positive scenario - valid User id
    // JUnit test for GET UserInfo by lastname and firstname REST API
    @Test
    void findByFirstnameAndLastname() throws Exception{
        // given - precondition or setup
        String firstname = "Test";
        String lastname = "test";
        UserInfo userInfo = UserInfo.builder()
                .firstname("Test")
                .lastname("test")
                .username("test@gmail.com")
                .password("123456")
                .build();
        given(staffService.findByFirstnameAndLastname(firstname,lastname)).willReturn(Optional.of(userInfo));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/staffs/{firstname}/{lastname}", firstname ,lastname));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstname",is(userInfo.getFirstname())))
                .andExpect(jsonPath("$.lastname",is(userInfo.getLastname())))
                .andExpect(jsonPath("$.username",is(userInfo.getUsername())))
                .andExpect(jsonPath("$.password",is(userInfo.getPassword())));
    }

    @Test
    void findByInvalidFirstnameAndInvalidLastname() throws Exception{
        // given - precondition or setup
        String firstname = "Test";
        String lastname = "test";
        UserInfo userInfo = UserInfo.builder()
                .firstname("Test")
                .lastname("test")
                .username("test@gmail.com")
                .password("123456")
                .build();
        given(staffService.findByFirstnameAndLastname(firstname,lastname)).willReturn(Optional.empty());
        // when -  action or the behaviour that we are going test

        // then - verify the output
    }

    // positive scenario - valid employee id
    // JUnit test for GET employee by id REST API
    @Test
    void getUserInfoById() throws Exception{
        // given - precondition or setup
        int userId = 1;
        UserInfo userInfo = UserInfo.builder()
                .firstname("Test")
                .lastname("test")
                .username("test@gmail.com")
                .password("123456")
                .build();
        given(staffService.getUserInfoById(userId)).willReturn(Optional.of(userInfo));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/staffs/{theId}", userId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstname",is(userInfo.getFirstname())))
                .andExpect(jsonPath("$.lastname",is(userInfo.getLastname())))
                .andExpect(jsonPath("$.username",is(userInfo.getUsername())))
                .andExpect(jsonPath("$.password",is(userInfo.getPassword())));
    }
    // negative scenario - valid employee id
    // JUnit test for GET employee by id REST API
    @Test
    void getUserInfoByInvalidId() throws Exception{
        // given - precondition or setup
        int userId = 1;
        UserInfo userInfo = UserInfo.builder()
                .firstname("Test")
                .lastname("test")
                .username("test@gmail.com")
                .password("123456")
                .build();
        given(staffService.getUserInfoById(userId)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/staffs/{theId}", userId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for update UserInfo REST API - positive scenario
    @Test
    void updateUserInfo() throws Exception{
        // given - precondition or setup
        int userId = 1;
        UserInfo saveUserInfo = UserInfo.builder()
                .id(1)
                .firstname("Test")
                .lastname("test")
                .username("test@gmail.com")
                .password("123456")
                .build();

        UserInfo updatedUserInfo = UserInfo.builder()
                .id(1)
                .firstname("L")
                .lastname("Y")
                .username("Lex@gmail.com")
                .password("123456")
                .build();
        given(staffService.getUserInfoById(userId)).willReturn(Optional.of(saveUserInfo));
        given(staffService.updateUserInfo(any(UserInfo.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        // when -  action or the behaviour that we are going test
        ResultActions response =
                mockMvc.perform(put("/staffs/{theId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUserInfo)));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(updatedUserInfo.getId())))
                .andExpect(jsonPath("$.username",is(updatedUserInfo.getUsername())))
                .andExpect(jsonPath("$.password",is(updatedUserInfo.getPassword())))
                .andExpect(jsonPath("$.firstname",is(updatedUserInfo.getFirstname())))
                .andExpect(jsonPath("$.lastname",is(updatedUserInfo.getLastname())));
    }
    // JUnit test for update employee REST API - negative scenario
    @Test
    void updateUserInfoFailed() throws Exception{
        // given - precondition or setup
        int userId = 1;
        UserInfo saveUserInfo = UserInfo.builder()
                .firstname("Test")
                .lastname("test")
                .username("test@gmail.com")
                .password("123456")
                .build();

        UserInfo updatedUserInfo = UserInfo.builder()
                .firstname("L")
                .lastname("Y")
                .username("Lex@gmail.com")
                .password("123456")
                .build();
        given(staffService.getUserInfoById(userId)).willReturn(Optional.empty());
        given(staffService.updateUserInfo(any(UserInfo.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        // when -  action or the behaviour that we are going test
        ResultActions response =
                mockMvc.perform(put("/staffs/{theId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUserInfo)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for delete employee REST API
    @Test
    void deleteUserInfo() throws Exception{
        // given - precondition or setup
        int userId = 1;
        willDoNothing().given(staffService).deleteUserInfo(userId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/staffs/{theId}", userId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}