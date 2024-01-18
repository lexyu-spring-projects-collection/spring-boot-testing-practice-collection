package com.lex.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Lex Yu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private Geo geo;
}
