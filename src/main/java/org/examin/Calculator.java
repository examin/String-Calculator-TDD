package org.examin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
	private static String regex = "\\/\\/(.)\\n(.*)";
	private static String defaultDelimiter = ",";

	public int add(String text) {
		if (text.equals("")) {
			return 0;
		} else {
			String delimiter = defaultDelimiter;
			String inputNumList = text;
			if(text.matches(regex)){
				delimiter = extractDelimterFromInput(text);
				inputNumList = extractNumString(text);
			}
			inputNumList = replaceCommonDelimiters(inputNumList);
			String numList[] = splitNumbers(inputNumList, delimiter);
			return sum(numList);
		}
	}

	private String extractNumString(String text) {
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(text);
		if(matcher.find()){
			return matcher.group(2);
		}
		return "";
	}

	private String extractDelimterFromInput(String text) {
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(text);
		if(matcher.find()){
			return matcher.group(1);
		}
		return "";
	}

	private String replaceCommonDelimiters(String text) {
		return text.replaceAll("\n", ",");
	}

	private static String[] splitNumbers(String numbers, String splitter) {
		return numbers.split(splitter);
	}

	private static int sum(String[] numbers) {
		int total = 0;
		for (String number : numbers) {
			total += Integer.parseInt(number);
		}
		return total;
	}
}
