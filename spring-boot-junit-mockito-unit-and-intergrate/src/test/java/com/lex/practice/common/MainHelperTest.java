package com.lex.practice.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Lex Yu
 */
class MainHelperTest {

	@Mock
	Helper helper;
	@InjectMocks
	MainHelper mainHelper = new MainHelper();

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void  test_method_under_test_greater_than_5(){
		mainHelper.methodUnderTest(7);

		Mockito.verify(helper).getString();
	}


	@Test
	void  test_method_under_test_lower_than_5(){
		mainHelper.methodUnderTest(3);

		Mockito.verify(helper).getString();
	}



}