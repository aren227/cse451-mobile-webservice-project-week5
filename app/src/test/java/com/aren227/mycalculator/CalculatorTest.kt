package com.aren227.mycalculator

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {
    private val calculator = Calculator()

    @Test
    fun test_add_01() {
        assertEquals(110.0, calculator.add(100.0, 10.0), 0.0)
    }

    @Test
    fun test_add_02() {
        assertEquals(90.0, calculator.add(100.0, -10.0), 0.0)
    }

    @Test
    fun test_subtract_01() {
        assertEquals(90.0, calculator.subtract(100.0, 10.0), 0.0)
    }

    @Test
    fun test_subtract_02() {
        assertEquals(110.0, calculator.subtract(100.0, -10.0), 0.0)
    }

    @Test
    fun test_divide_01() {
        assertEquals(10.0, calculator.divide(100.0, 10.0), 0.0)
    }

    @Test
    fun test_divide_02() {
        // NOTE(aren): 슬라이드와 다르지만, A/B의 값은 inf가 나오는게 fp 연산규칙상 맞아서 수정
        assertEquals(Double.POSITIVE_INFINITY, calculator.divide(100.0, 0.0), 0.0)
    }

    @Test
    fun test_divide_03() {
        assertEquals(2.5, calculator.divide(10.0, 4.0), 0.0)
    }

    @Test
    fun test_multiply_01() {
        assertEquals(1000.0, calculator.multiply(100.0, 10.0), 0.0)
    }

    @Test
    fun test_multiply_02() {
        assertEquals(100.0, calculator.multiply(100.0, 1.0), 0.0)
    }
}
