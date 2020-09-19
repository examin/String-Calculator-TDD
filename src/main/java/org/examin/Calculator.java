package org.examin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
	private static String oneCharDelimtedRegex = "\\/\\/(.)\\n(.*)";
	private static String longLenDelimitedRegex = "\\/\\/\\[(.+)\\]\\n(.*)";
	private static String defaultDelimiter = ",";

	public int add(String text) {
		if (text.equals("")) {
			return 0;
		} else {
			String delimiter = defaultDelimiter;
			String inputNumList = text;
			if (text.matches(oneCharDelimtedRegex) || text.matches(longLenDelimitedRegex)) {
				delimiter = extractDelimterFromInput(text);
				inputNumList = extractNumString(text);
			}
			inputNumList = replaceDelimiterWithComma(inputNumList,delimiter);
			String numList[] = splitNumbers(inputNumList, ",");
			return sum(numList);
		}
	}

	private String extractNumString(String text) {
		Pattern oneCharDelimtedPattern = Pattern.compile(oneCharDelimtedRegex, Pattern.MULTILINE);
		Matcher oneCharDelimtedMatcher = oneCharDelimtedPattern.matcher(text);
		if (oneCharDelimtedMatcher.find()) {
			return oneCharDelimtedMatcher.group(2);
		}
		Pattern longLenDelimitedPattern = Pattern.compile(longLenDelimitedRegex, Pattern.MULTILINE);
		Matcher longLenDelimitedMatcher= longLenDelimitedPattern.matcher(text);
		if(longLenDelimitedMatcher.find()){
			return longLenDelimitedMatcher.group(2);
		}
		return "";
	}

	private String extractDelimterFromInput(String text) {
		Pattern oneCharDelimtedPattern = Pattern.compile(oneCharDelimtedRegex, Pattern.MULTILINE);
		Matcher oneCharDelimtedMatcher = oneCharDelimtedPattern.matcher(text);
		if (oneCharDelimtedMatcher.find()) {
			return oneCharDelimtedMatcher.group(1);
		}
		Pattern longLenDelimitedPattern = Pattern.compile(longLenDelimitedRegex, Pattern.MULTILINE);
		Matcher longLenDelimitedMatcher= longLenDelimitedPattern.matcher(text);
		if(longLenDelimitedMatcher.find()){
			return longLenDelimitedMatcher.group(1);
		}
		return "";
	}

	private String replaceDelimiterWithComma(String text, String delimiter) {
		return text.replace("\n", ",")
				.replace(delimiter,",");
	}

	private static String[] splitNumbers(String numbers, String splitter) {
		return numbers.split(splitter);
	}

	private static int sum(String[] numbers) {
		int postiveNumsTotalSum = 0;
		StringBuilder negString = new StringBuilder();
		for (String number : numbers) {
			int currNum = Integer.parseInt(number);
			if (currNum < 0) {
				negString.append("," + number);
			}
			if (currNum < 1000) {
				postiveNumsTotalSum += currNum;
			}
		}
		if (negString.length() > 0) {
			throw new IllegalArgumentException("Negatives not allowed: " + negString.substring(1));
		}
		return postiveNumsTotalSum;
	}
}
