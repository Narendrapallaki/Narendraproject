package com.example.IgnoreAnnotaion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import com.example.Entity.Calculation;

public class CalculationTest {

	@Ignore
	public void sumTest() {
		int expectSum = 1;
		int a = 5;
		int b = 6;
		Calculation cal = new Calculation();
		int sum = cal.sum(a, b);

		assertEquals(expectSum, sum);

	}
	@Test
	public void sumTest1() {
		int expectSum = 11;
		int a = 5;
		int b = 6;
		Calculation cal = new Calculation();
		int sum = cal.sum(a, b);

		assertEquals(expectSum, sum);

	}

	
}
