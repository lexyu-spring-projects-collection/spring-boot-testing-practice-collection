package com.lex.practice.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/2/23 上午 11:31
 */
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    /*
    Calculator calculator;
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
     */

    @Test
    void multiply() {
//        assertEquals(10, calculator.multiply(4, 5));
        assertEquals(25, calculator.multiply(5, 5));
    }

    @Test
    void testDivide(){
        assertEquals(2, calculator.divide(10,5));
//        assertEquals(2, calculator.divide(4,0));
    }

}