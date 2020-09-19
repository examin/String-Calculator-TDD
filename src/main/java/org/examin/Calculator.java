package org.examin;

public class Calculator {
	public int add(String text) {
		if(text.equals("")){
			return 0;
		}
		else{
			text = replaceOtherDelimiters(text);
			String numList[] = splitNumbers(text, ",");
			return sum(numList);
		}
	}

	private String replaceOtherDelimiters(String text) {
		return text.replaceAll("\n", ",");
	}

	private static String[] splitNumbers(String numbers, String splitter){
		return numbers.split(splitter);
	}

	private static int sum(String[] numbers){
		int total = 0;
		for(String number : numbers){
			total += Integer.parseInt(number);
		}
		return total;
	}
}
