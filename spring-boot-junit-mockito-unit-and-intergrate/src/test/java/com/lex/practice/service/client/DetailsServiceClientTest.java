package com.lex.practice.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.practice.SpringBootUnitTestingApplicationTests;
import com.lex.practice.entity.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


/**
 * @author : Lex Yu
 */
@ExtendWith(SpringExtension.class)
@RestClientTest(value = {DetailsServiceClient.class, SpringBootUnitTestingApplicationTests.class})
class DetailsServiceClientTest {
	@Autowired
	private DetailsServiceClient client;
	@Autowired
	private MockRestServiceServer server;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void init() throws Exception {
		String detailsString = objectMapper.writeValueAsString(new Details("John Smith", "john"));
		this.server.expect(requestTo("/john/details"))
				.andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
	}

	@Test
	void test_get_user_details() {
		Details details = this.client.getUserDetails("john");

		assertThat(details.getLogin()).isEqualTo("john");
		assertThat(details.getName()).isEqualTo("John Smith");
	}
}