package com.lex.practice.redis.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import java.io.IOException;


/**
 * @author : Lex Yu
 */
@TestConfiguration
public class RedisConfigurationTest {
	private RedisServer redisServer;

	public RedisConfigurationTest(RedisProperties redisProperties) throws IOException {
		this.redisServer = new RedisServer(redisProperties.getRedisPort());
	}

	@PostConstruct
	public void postConstruct() throws IOException {
		redisServer.start();
	}

	@PreDestroy
	public void preDestroy() throws IOException {
		redisServer.stop();
	}
}