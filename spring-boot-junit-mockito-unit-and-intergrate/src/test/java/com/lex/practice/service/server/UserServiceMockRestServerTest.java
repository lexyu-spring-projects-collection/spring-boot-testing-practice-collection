package com.lex.practice.service.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.practice.entity.Address;
import com.lex.practice.entity.Company;
import com.lex.practice.entity.Geo;
import com.lex.practice.entity.User;
import com.lex.practice.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Lex Yu
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceMockRestServerTest {

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@Autowired
	private ObjectMapper mapper;

	private User expected_user;



	@BeforeEach
	public void init() {
		mockServer = MockRestServiceServer.createServer(restTemplate);

		expected_user = new User(1,
				"Leanne Graham",
				"Bret",
				"Sincere@april.biz",
				new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874",
						new Geo("-37.3159", "81.1496")),
				"1-770-736-8031 x56442", "hildegard.org",
				new Company("Romaguera-Crona",
						"Multi-layered client-server neural-net",
						"harness real-time e-markets"));
	}

	@Test
	void test_get_user() throws JsonProcessingException {
		mockServer.expect(ExpectedCount.once(),
				MockRestRequestMatchers.requestTo("https://jsonplaceholder.typicode.com/users/1"))
				.andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
				.andRespond(MockRestResponseCreators
						.withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(mapper.writeValueAsString(expected_user)));

		User actual_user = userService.getUser(1);
		mockServer.verify();

		assertEquals(expected_user, actual_user);
	}
}
