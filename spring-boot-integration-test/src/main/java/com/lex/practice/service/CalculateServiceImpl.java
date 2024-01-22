package com.lex.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : Lex Yu
 */
@Slf4j
@Service
public class CalculateServiceImpl implements CalculateService{
	@Override
	public int add(int a, int b) {
		log.info("calculate a + b");
		return a + b;
	}
}
