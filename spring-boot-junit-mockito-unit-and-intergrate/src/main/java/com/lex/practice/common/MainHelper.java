package com.lex.practice.common;

/**
 * @author : Lex Yu
 */
public class MainHelper {
	Helper helper = new Helper();

	String methodUnderTest(int i) {
		if (i > 5) {
			return helper.getString();
		}
		return "Hello";
	}
}

class Helper {
	String getString() {
		return """
				This is for
				Wanted but not invoked:
				...
				Actually, there were zero interactions with this mock.
				""";
	}
}
