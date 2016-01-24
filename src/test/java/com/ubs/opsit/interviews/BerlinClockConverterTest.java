package com.ubs.opsit.interviews;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ubs.opsit.interviews.BerlinClockConverter;

public class BerlinClockConverterTest {

	@Test
	public void testConvertTimeMidnight00() {
		String expectedBCTime = "Y\r\n"+"OOOO\r\n"+"OOOO\r\n"+"OOOOOOOOOOO\r\n"+"OOOO";
		String midnight00 = "00:00:00";
		assertEquals(expectedBCTime,new BerlinClockConverter().convertTime(midnight00));
	}

	@Test
	public void testConvertTimeEarlyMornig() {
		String expectedBCTime = "Y\r\n"+"ROOO\r\n"+"RROO\r\n"+"YYROOOOOOOO\r\n"+"YYOO";
		String midnightEarlyMornig = "07:17:36";
		assertEquals(expectedBCTime,new BerlinClockConverter().convertTime(midnightEarlyMornig));
	}

	@Test
	public void testConvertTimMiddleOfTheAfternoon() {
		String expectedBCTime = "O\r\n"+"RROO\r\n"+"RRRO\r\n"+"YYROOOOOOOO\r\n"+"YYOO";
		String midnightMiddleOfTheAfternoon = "13:17:01";
		assertEquals(expectedBCTime,new BerlinClockConverter().convertTime(midnightMiddleOfTheAfternoon));
	}

	@Test
	public void testConvertTimeJustBeforeMidnight() {
		String expectedBCTime = "O\r\n"+"RRRR\r\n"+"RRRO\r\n"+"YYRYYRYYRYY\r\n"+"YYYY";
		String midnightJustBeforeMidnight = "23:59:59";
		assertEquals(expectedBCTime,new BerlinClockConverter().convertTime(midnightJustBeforeMidnight));
	}

	@Test
	public void testConvertTimeMidnight24() {
		String expectedBCTime = "Y\r\n"+"RRRR\r\n"+"RRRR\r\n"+"OOOOOOOOOOO\r\n"+"OOOO";
		String midnight24 = "24:00:00";
		assertEquals(expectedBCTime,new BerlinClockConverter().convertTime(midnight24));
	}

}
