package com.ubs.opsit.interviews.input;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

public class MultipleRowClockTimeTest {

	@Test
	public void testMultipleRowClockTime() {
		String expectedSeconds = "59";
		String expectedMinutes = "04";
		String expectedHour = "23";
		MultipleRowClockTime testedBCTime = new MultipleRowClockTime(expectedHour+":"+expectedMinutes+":"+expectedSeconds);
		assertTrue(Integer.valueOf(expectedHour).equals(testedBCTime.getHour())
				&& Integer.valueOf(expectedMinutes).equals(testedBCTime.getMinutes())
				&& Integer.valueOf(expectedSeconds).equals(testedBCTime.getSeconds()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultipleRowClockTimeShouldThrowException() {
		String expectedSeconds = "59";
		String expectedMinutes = "61";
		String expectedHour = "23";
		new MultipleRowClockTime(expectedHour+":"+expectedMinutes+":"+expectedSeconds);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultipleRowClockTimeShouldThrowExceptionWhenNull() {
		new MultipleRowClockTime(null);
	}

	@Test
	public void testVerifyTimeFormatMidnight24() {
		String inputTime = "24:00:00";
		assertTrue(MultipleRowClockTime.verifyTimeFormat(inputTime));
	}
	
	@Test
	public void testVerifyTimeFormatMidnight00() {
		String inputTime = "00:00:00";
		assertTrue(MultipleRowClockTime.verifyTimeFormat(inputTime));
	}
	
	@Test
	public void testVerifyTimeFormatMiddleOfTheAfternoon() {
		String inputTime = "13:17:01";
		assertTrue(MultipleRowClockTime.verifyTimeFormat(inputTime));
	}
	
	@Test
	public void testVerifyTimeFormatJustBeforeMidnight() {
		String inputTime = "23:59:59";
		assertTrue(MultipleRowClockTime.verifyTimeFormat(inputTime));
	}

	@Test
	public void testVerifyTimeFormatWhenWrongFormat() {
		String inputTime = "25:01:00";
		assertFalse(MultipleRowClockTime.verifyTimeFormat(inputTime));
	}
	
	
	@Test
	public void testVerifyTimeFormatWhenNull() {
		assertFalse(MultipleRowClockTime.verifyTimeFormat(null));
	}
	
	@Test
	public void testVerifyTimeFormatWhenEmpty() {
		assertFalse(MultipleRowClockTime.verifyTimeFormat(""));
	}
	
}
