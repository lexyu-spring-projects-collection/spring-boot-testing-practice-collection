package com.lex.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Lex Yu
 * @date : 2023/8/28
 */
@RestController
public class AdminController {

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public void test(){
		System.out.println("admin");
	}
}
