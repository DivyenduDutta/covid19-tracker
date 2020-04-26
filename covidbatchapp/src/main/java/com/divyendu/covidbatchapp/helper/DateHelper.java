package com.divyendu.covidbatchapp.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date Helper class
 */
public class DateHelper {
	
	/*
	 * Converts Date object to String
	 */
	public static String convertDateToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);  
	}
	
	/*
	 * Converts String to Date object
	 */
	public static Date convertStringToDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date); 
	}

}
