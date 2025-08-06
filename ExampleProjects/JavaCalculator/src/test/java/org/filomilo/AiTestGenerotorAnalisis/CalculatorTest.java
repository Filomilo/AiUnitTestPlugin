package org.filomilo.AiTestGenerotorAnalisis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void addInt() {
        assertEquals(3, Calculator.add(1, 2));
    }

    @Test
    void subtractInt() {
        assertEquals(-1, Calculator.subtract(1, 2));
    }

    @Test
    void multiplyInt() {
        assertEquals(6, Calculator.multiply(3, 2));
    }

    @Test
    void divideInt() {
    assertEquals(3, Calculator.divide(6, 2));
    assertThrows(ArithmeticException.class, () -> Calculator.divide(-1, 0));
    }


    @Test
    void addFloat() {
        assertEquals(1.5f, Calculator.add(1, 0.5f));
    }

    @Test
    void subtractFloat() {
    assertEquals(0.5f, Calculator.subtract(1, 0.5f));
    }

    @Test
    void multiplyFloat() {
    assertEquals(1.5f, Calculator.multiply(3, 0.5f));
    }

    @Test
    void divideFloat() {
        assertEquals(12f, Calculator.divide(6, 0.5f));
        assertThrows(ArithmeticException.class, () -> Calculator.divide(-1f, 0f));
    }




    @Test
    void addDouble() {
        assertEquals(3.2, Calculator.add(1.1, 2.1));
    }

    @Test
    void subtractDouble() {
        assertEquals(2.9, Calculator.subtract(5, 2.1));
    }

    @Test
    void multiplyDouble() {
        assertTrue(Math.abs(Calculator.multiply(3, 2.1)-6.3)<0.0001);

    }

    @Test
    void divideDouble() {
        assertEquals(2.4, Calculator.divide(6, 2.5));
        assertThrows(ArithmeticException.class, () -> Calculator.divide(-1.0, 0.0));

    }




}