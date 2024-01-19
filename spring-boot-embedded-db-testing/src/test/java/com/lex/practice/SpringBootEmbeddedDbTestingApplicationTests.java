package com.lex.practice;

import com.lex.practice.entity.User;
import com.lex.practice.redis.config.RedisConfigurationTest;
import com.lex.practice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RedisConfigurationTest.class)
class SpringBootEmbeddedDbTestingApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void shouldSaveUser_toRedis() {
		final UUID id = UUID.randomUUID();
		final User user = new User(id, "name");

		final User saved = userRepository.save(user);

		System.out.println(saved);
		assertNotNull(saved);
	}
}
