package com.lex.practice.service.user;

import com.lex.practice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Lex Yu
 */
@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;

	public User getUser(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<User> resp =
				restTemplate.exchange("https://jsonplaceholder.typicode.com/users/" + id,
						HttpMethod.GET, httpEntity, User.class);

		return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
	}
}
