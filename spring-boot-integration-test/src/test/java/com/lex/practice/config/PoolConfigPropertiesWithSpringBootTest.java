package com.lex.practice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

/**
 * @author : Lex Yu
 */
@SpringBootTest(classes = {PoolConfigPropertiesWithSpringBootTest.InnerConfig.class, PoolConfigProperties.class})
public class PoolConfigPropertiesWithSpringBootTest {
	@Autowired
	private PoolConfigProperties poolConfigProperties;

	@Autowired
	ApplicationContext applicationContext;

	@Configuration
	@EnableConfigurationProperties
	protected static class InnerConfig {

	}

	@Test
	void getMaxTotal() {
		System.out.println(applicationContext.getBeanDefinitionCount());
		Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);


		int maxTotal = poolConfigProperties.getMaxTotal();
		Assertions.assertEquals(0, maxTotal);
	}

}
