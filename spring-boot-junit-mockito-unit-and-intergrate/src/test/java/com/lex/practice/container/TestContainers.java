package com.lex.practice.container;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * @author : Lex Yu
 * @date : 2023/9/4
 */
@Testcontainers(disabledWithoutDocker = true)
public abstract class TestContainers {

	@Container
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
			DockerImageName.parse("postgres:15.1-alpine"))
			.withUsername("postgres")
			.withPassword("postgres");

	static {
		postgreSQLContainer.start();
	}

	@DynamicPropertySource
	static void overrideTestProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}
}
