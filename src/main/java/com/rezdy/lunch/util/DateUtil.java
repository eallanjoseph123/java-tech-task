package com.rezdy.lunch.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private DateUtil() {
		
	}
	
	public static boolean isValid(final String date) {
		try {
			LocalDate.parse(date, FORMATTER);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
}
