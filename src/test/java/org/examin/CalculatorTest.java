package org.examin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
	Calculator calculator = new Calculator();
	@Test
	public void testEmptyString() {
		assertEquals(0, calculator.add(""));
	}
}
