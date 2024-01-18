package com.lex.practice.service.job;

import com.lex.practice.entity.JobPosition;
import com.lex.practice.entity.Person;

import java.util.Optional;

/**
 * @author : Lex Yu
 */
public interface JobService {
	Optional<JobPosition> findCurrentJobPosition(Person person);

	default boolean assignJobPosition(Person person, JobPosition jobPosition) {
		if(!findCurrentJobPosition(person).isPresent()) {
			person.setCurrentJobPosition(jobPosition);
			return true;
		} else {
			return false;
		}
	}
}
