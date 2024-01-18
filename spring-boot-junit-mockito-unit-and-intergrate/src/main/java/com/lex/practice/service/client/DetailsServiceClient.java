package com.lex.practice.service.client;

import com.lex.practice.entity.Details;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Lex Yu
 */
@Service
public class DetailsServiceClient {

	private final RestTemplate restTemplate;

	public DetailsServiceClient(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();
	}

	public Details getUserDetails(String name) {
		return restTemplate.getForObject("/{name}/details", Details.class, name);
	}

}
