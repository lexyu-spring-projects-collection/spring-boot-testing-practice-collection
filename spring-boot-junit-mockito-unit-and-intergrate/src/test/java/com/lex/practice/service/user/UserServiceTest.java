package com.lex.practice.service.user;

import com.lex.practice.entity.Address;
import com.lex.practice.entity.Company;
import com.lex.practice.entity.Geo;
import com.lex.practice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Lex Yu
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private UserService userService = new UserService();

	private User expected_user;

	@BeforeEach
	void init() {
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
	void test_get_user() {
		Mockito.when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users/1", User.class))
				.thenReturn(new ResponseEntity<>(expected_user, HttpStatus.OK));

		User actual_user = userService.getUser(1);
		assertEquals(expected_user, actual_user);
	}


}