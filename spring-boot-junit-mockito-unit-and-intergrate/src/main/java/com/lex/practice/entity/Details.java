package com.lex.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Lex Yu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Details {
	private String name;
	private String login;
}
