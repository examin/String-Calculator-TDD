package org.examin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
	Calculator calculator = new Calculator();

	@Test
	public void testEmptyString() {
		assertEquals(0, calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, calculator.add("1,2"));
	}

	@Test
	public void testThreeNumbers() {
		assertEquals(6, calculator.add("1,2,3"));
	}

	@Test
	public void testLongLengOfNumbers() {
		assertEquals(3027, calculator.add("1,2,3,6,774,654,45,344,5,34,53,54,45,23,54,34,5,345,3,543"));
	}

	@Test
	public void testNewLine(){
		assertEquals(6, calculator.add("1\n2,3"));
	}

	@Test
	public void testOtherDelimiter(){
		assertEquals(3, calculator.add("//;\n1;2"));
	}

	@Test
	public void testNegativeNumver(){
		try {
			calculator.add("-1,2");
		}
		catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Negatives not allowed: -1");
		}

		try {
			calculator.add("2,-4,3,-5");
		}
		catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Negatives not allowed: -4,-5");
		}
	}


}
