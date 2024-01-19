package com.lex.practice.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author : Lex Yu
 */
@Configuration
public class RedisProperties {
	private int redisPort;
	private String redisHost;

	public RedisProperties(
			@Value("${spring.data.redis.port}") int redisPort,
			@Value("${spring.data.redis.host}") String redisHost) {
		this.redisPort = redisPort;
		this.redisHost = redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public String getRedisHost() {
		return redisHost;
	}
}
