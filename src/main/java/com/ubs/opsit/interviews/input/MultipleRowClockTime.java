package com.ubs.opsit.interviews.input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * @author mdie
 * 
 *  a class contains information about time (given in format HH:mm:ss, e.g. 23:54:09)
 *  accepts also this value (24:00:00)
 */
public class MultipleRowClockTime {

	private int hour;
	private int minutes;
	private int seconds;
	
	public MultipleRowClockTime(String inputTime) {
		
		if (!verifyTimeFormat(inputTime)) {
			throw new IllegalArgumentException(String.format("Format of input time is wrong: %s.",inputTime));
		}
		
		int[] inputTimeParts = Stream.of(inputTime.split(":")).mapToInt(Integer::parseInt).toArray();
		this.hour = inputTimeParts[0];
		this.minutes = inputTimeParts[1];
		this.seconds = inputTimeParts[2];
	}

	public int getHour() {
		return hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	protected static boolean verifyTimeFormat(String inputTime) {
		if (inputTime == null) {
			return false;
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss",Locale.US);
		timeFormat.setLenient(false);
		try {
			timeFormat.parse(inputTime);
		} catch (ParseException e) {
			return inputTime.equals("24:00:00");
		}
		
		return true;
	}
	
}
