package com.lex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.security.RolesAllowed;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true
//		, securedEnabled = true, jsr250Enabled = true
)
public class SecurityConfig {
	@Bean
	@Order(1)
	public SecurityFilterChain adminPageSecurity(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				.antMatcher("/admin")
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin();

		return httpSecurity.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain restApiSecurity(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				.antMatcher("/api/**")
				.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();

		return httpSecurity.build();
	}

	@Bean
	public JwtDecoder jwtDecoder(){
		return JwtDecoders.fromIssuerLocation("https://cognito-idp.ap-southeast-1.amazonaws.com/ap-southeast-1_NUqHlDkoF");
	}

}
