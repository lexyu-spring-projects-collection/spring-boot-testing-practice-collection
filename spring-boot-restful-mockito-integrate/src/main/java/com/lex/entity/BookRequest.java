package com.lex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Lex Yu
 * @date : 2023/8/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
	private String title;
	private int price;
	private LocalDate publishedDate;
}
