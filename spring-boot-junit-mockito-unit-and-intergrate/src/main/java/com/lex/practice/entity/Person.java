package com.lex.practice.entity;

/**
 * @author : Lex Yu
 */
public class Person {
	private String name;
	private JobPosition currentJobPosition;

	public Person() {}

	public Person(String name) {
		this.name = name;
	}

	public JobPosition getCurrentJobPosition() {
		return currentJobPosition;
	}

	public void setCurrentJobPosition(JobPosition currentJobPosition) {
		this.currentJobPosition = currentJobPosition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
