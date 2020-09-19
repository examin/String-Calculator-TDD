package org.examin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
	private static String oneCharDelimtedRegex = "\\/\\/(.)\\n(.*)";
	private static String longLenDelimitedRegex = "\\/\\/\\[([^\\[\\]]+)\\]\\n(.*)";
	private static String multipleDelimiterRegex = "\\/\\/((\\[.+\\]){2,})\\n(.*)";
	private static String defaultDelimiter = ",";

	public int add(String text) {
		if (text.equals("")) {
			return 0;
		} else {
			String inputNumList = text;
			if (text.matches(oneCharDelimtedRegex) || text.matches(longLenDelimitedRegex)) {
				inputNumList = getNumStringFromSingleDelimtedInput(text);
			} else if (text.matches(multipleDelimiterRegex)) {
				inputNumList = getNumStringFromMultiDelimtedInput(text);
			} else {
				inputNumList = replaceDelimiterWithComma(text, "\n");
			}
			String numList[] = splitNumbers(inputNumList, ",");
			return sum(numList);
		}
	}

	private String getNumStringFromMultiDelimtedInput(String text) {
		String delimiters = "";
		String numsString = "";
		Pattern multiDelimterPattern = Pattern.compile(multipleDelimiterRegex, Pattern.MULTILINE);
		Matcher multiDelimterMatcher = multiDelimterPattern.matcher(text);
		if (multiDelimterMatcher.find()) {
			delimiters = multiDelimterMatcher.group(1);
			numsString = multiDelimterMatcher.group(3);
		}
		return replaceMutiDelimitersWithComma(numsString, delimiters);
	}

	private String replaceMutiDelimitersWithComma(String numsString, String delimiters) {
		String delimtersArr[] = delimiters.substring(1,delimiters.length()-1).split("\\]\\[");
		for(String delimiter : delimtersArr){
			numsString = numsString.replace(delimiter,",");
		}
		return numsString;
	}


	private String getNumStringFromSingleDelimtedInput(String text) {
		String delimiter = "";
		String numsString = "";
		Pattern oneCharDelimtedPattern = Pattern.compile(oneCharDelimtedRegex, Pattern.MULTILINE);
		Matcher oneCharDelimtedMatcher = oneCharDelimtedPattern.matcher(text);
		if (oneCharDelimtedMatcher.find()) {
			delimiter = oneCharDelimtedMatcher.group(1);
			numsString = oneCharDelimtedMatcher.group(2);
		}
		Pattern longLenDelimitedPattern = Pattern.compile(longLenDelimitedRegex, Pattern.MULTILINE);
		Matcher longLenDelimitedMatcher = longLenDelimitedPattern.matcher(text);
		if (longLenDelimitedMatcher.find()) {
			delimiter = longLenDelimitedMatcher.group(1);
			numsString = longLenDelimitedMatcher.group(2);
		}

		return replaceDelimiterWithComma(numsString, delimiter);
	}

	private String replaceDelimiterWithComma(String text, String delimiter) {
		return text.replace(delimiter, ",");
	}

	private static String[] splitNumbers(String numbers, String splitter) {
		return numbers.split(splitter);
	}

	private static int sum(String[] numbers) {
		int postiveNumsTotalSum = 0;
		StringBuilder negString = new StringBuilder();
		for (String number : numbers) {
			try {
				int currNum = Integer.parseInt(number);
				if (currNum < 0) {
					negString.append("," + number);
				}
				if (currNum < 1000) {
					postiveNumsTotalSum += currNum;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (negString.length() > 0) {
			throw new IllegalArgumentException("Negatives not allowed: " + negString.substring(1));
		}
		return postiveNumsTotalSum;
	}
}
