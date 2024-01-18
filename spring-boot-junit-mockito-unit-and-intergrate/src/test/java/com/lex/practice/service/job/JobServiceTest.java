package com.lex.practice.service.job;

import com.lex.practice.entity.JobPosition;
import com.lex.practice.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Lex Yu
 */
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JobServiceTest {

	@Mock
	private JobService jobService;

	private Person person;

	private JobPosition jobPosition;

	@BeforeEach
	void init() {
		person = new Person();
		jobPosition = new JobPosition();
	}


	@Test
	public void findCurrentJobPosition() {

		Mockito.when(jobService.findCurrentJobPosition(person))
				.thenReturn(Optional.of(jobPosition));
		Mockito.doCallRealMethod().when(jobService)
				.assignJobPosition(
						Mockito.any(Person.class),
						Mockito.any(JobPosition.class)
				);

		assertFalse(jobService.assignJobPosition(person, jobPosition));
	}

}