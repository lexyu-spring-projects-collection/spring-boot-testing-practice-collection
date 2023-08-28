package com.lex.controller;

import com.lex.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
@Import(SecurityConfig.class)
@WebMvcTest(AdminController.class)
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(roles = "ADMIN")
	@Test
	void shouldReturnAdminView() throws Exception {
		this.mockMvc
				.perform(get("/admin"))
				.andExpect(status().is(200));
	}
}