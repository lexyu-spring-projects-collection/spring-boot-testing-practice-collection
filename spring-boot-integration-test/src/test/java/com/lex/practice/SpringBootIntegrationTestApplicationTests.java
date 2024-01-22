package com.lex.practice;

import com.lex.practice.config.OuterConfigTest;
import com.lex.practice.service.CalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

@ActiveProfiles("test")
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.NONE
		, classes = {OuterConfigTest.class}
)
class SpringBootIntegrationTestApplicationTests {
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	CalculateService calculateService;

	/**
	 * Will Not be Loaded
	 */
	@Configuration
	protected static class InnerConfig {
	}


	/**
	 * 如果使用内部静态类 @TestConfiguration，依然回去查找 @SpringBootConfiguration
	 */
	@TestConfiguration
	protected static class InnerConfigTest {

	}

	@Test
	void contextLoads() {
		System.out.println("Bean count = " + appContext.getBeanDefinitionCount());
		Stream.of(appContext.getBeanDefinitionNames()).forEach(System.out::println);
	}

	@Test
	void add() {
		int result = calculateService.add(1, 2);
		Assertions.assertEquals(3, result);
	}

}
