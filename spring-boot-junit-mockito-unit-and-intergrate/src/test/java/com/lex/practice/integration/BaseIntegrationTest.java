package com.lex.practice.integration;

import com.lex.practice.container.TestContainers;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

/**
 * @author : Lex Yu
 * @date : 2023/9/4
 */
@EntityScan("com.lex.practice.entity")
@EnableJpaRepositories("com.lex.practice.repository")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseIntegrationTest extends TestContainers {
}
