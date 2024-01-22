package com.lex.practice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Lex Yu
 */
@ConfigurationProperties(prefix = "pool")
@Component
public class PoolConfigProperties {
	private int maxTotal;
	private int minIdle;

	private String threadCount;

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public String getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(String threadCount) {
		this.threadCount = threadCount;
	}
}
