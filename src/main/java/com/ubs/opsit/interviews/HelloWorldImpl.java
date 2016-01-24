package com.ubs.opsit.interviews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author mdie
 *
 * example implementation of HelloWorld for interview
 */
public class HelloWorldImpl implements HelloWorld {

	private static SimpleDateFormat inputDateFormat;
	private final static SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("EEEE", Locale.US);
	
	
	static {
		inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		inputDateFormat.setLenient(false);
	}
	
	@Override
	public String sayHelloAndDayOfTheWeek(String date) {
		try {
			return String.format("Hello World on %s", dayOfWeekFormat.format(inputDateFormat.parse(date)));
		} catch (ParseException e) {
			throw new IllegalArgumentException(String.format("Incorrect input date format [%s].;", date), e);
		}
	}

}
