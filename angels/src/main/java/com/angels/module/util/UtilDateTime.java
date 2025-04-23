package com.angels.module.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilDateTime {
	
	public static String addNowTimeString(String date) {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("HH:MM:SS"));
		return date + " " + localDateTimeString;
	}
	
	
	public static String add00TimeString(String date) {
		return date + " 00:00:00";
	}

	
	public static String add59TimeString(String date) {
		return date + " 23:59:59";
	}
	
	public static String nowString() {
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    return now.format(formatter);
	}

}
