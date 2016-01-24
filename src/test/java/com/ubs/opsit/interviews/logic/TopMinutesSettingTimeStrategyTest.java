package com.ubs.opsit.interviews.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

public class TopMinutesSettingTimeStrategyTest {

	private TopMinutesSettingTimeStrategy strategy = new TopMinutesSettingTimeStrategy();
	
	@Test
	public void testShouldLampBeEnabledJustBeforeMidnight() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
		assertTrue(strategy.shouldLampBeEnabled(2, time));
		assertTrue(strategy.shouldLampBeEnabled(3, time));
		assertTrue(strategy.shouldLampBeEnabled(4, time));
		assertTrue(strategy.shouldLampBeEnabled(5, time));
		assertTrue(strategy.shouldLampBeEnabled(6, time));
		assertTrue(strategy.shouldLampBeEnabled(7, time));
		assertTrue(strategy.shouldLampBeEnabled(8, time));
		assertTrue(strategy.shouldLampBeEnabled(9, time));
		assertTrue(strategy.shouldLampBeEnabled(10, time));
		assertTrue(strategy.shouldLampBeEnabled(11, time));
	}

	@Test
	public void testShouldLampBeEnabledMidnight24() {
		MultipleRowClockTime time = new MultipleRowClockTime("24:00:00");
		assertFalse(strategy.shouldLampBeEnabled(1, time));
		assertFalse(strategy.shouldLampBeEnabled(2, time));
		assertFalse(strategy.shouldLampBeEnabled(3, time));
		assertFalse(strategy.shouldLampBeEnabled(4, time));
		assertFalse(strategy.shouldLampBeEnabled(5, time));
		assertFalse(strategy.shouldLampBeEnabled(6, time));
		assertFalse(strategy.shouldLampBeEnabled(7, time));
		assertFalse(strategy.shouldLampBeEnabled(8, time));
		assertFalse(strategy.shouldLampBeEnabled(9, time));
		assertFalse(strategy.shouldLampBeEnabled(10, time));
		assertFalse(strategy.shouldLampBeEnabled(11, time));
	}

	@Test
	public void testShouldLampBeEnabledJustAfterMidnight() {
		MultipleRowClockTime time = new MultipleRowClockTime("00:26:00");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
		assertTrue(strategy.shouldLampBeEnabled(2, time));
		assertTrue(strategy.shouldLampBeEnabled(3, time));
		assertTrue(strategy.shouldLampBeEnabled(4, time));
		assertTrue(strategy.shouldLampBeEnabled(5, time));
		assertFalse(strategy.shouldLampBeEnabled(6, time));
		assertFalse(strategy.shouldLampBeEnabled(7, time));
		assertFalse(strategy.shouldLampBeEnabled(8, time));
		assertFalse(strategy.shouldLampBeEnabled(9, time));
		assertFalse(strategy.shouldLampBeEnabled(10, time));
		assertFalse(strategy.shouldLampBeEnabled(11, time));
	}

	@Test
	public void testShouldLampBeEnabledMiddleOfTheAfternoon() {
		MultipleRowClockTime time = new MultipleRowClockTime("13:37:01");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
		assertTrue(strategy.shouldLampBeEnabled(2, time));
		assertTrue(strategy.shouldLampBeEnabled(3, time));
		assertTrue(strategy.shouldLampBeEnabled(4, time));
		assertTrue(strategy.shouldLampBeEnabled(5, time));
		assertTrue(strategy.shouldLampBeEnabled(6, time));
		assertTrue(strategy.shouldLampBeEnabled(7, time));
		assertFalse(strategy.shouldLampBeEnabled(8, time));
		assertFalse(strategy.shouldLampBeEnabled(9, time));
		assertFalse(strategy.shouldLampBeEnabled(10, time));
		assertFalse(strategy.shouldLampBeEnabled(11, time));
	}
	
	
	@Test
	public void testShouldLampBeEnabledEarlyMornig() {
		MultipleRowClockTime time = new MultipleRowClockTime("07:46:03");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
		assertTrue(strategy.shouldLampBeEnabled(2, time));
		assertTrue(strategy.shouldLampBeEnabled(3, time));
		assertTrue(strategy.shouldLampBeEnabled(4, time));
		assertTrue(strategy.shouldLampBeEnabled(5, time));
		assertTrue(strategy.shouldLampBeEnabled(6, time));
		assertTrue(strategy.shouldLampBeEnabled(7, time));
		assertTrue(strategy.shouldLampBeEnabled(8, time));
		assertTrue(strategy.shouldLampBeEnabled(9, time));
		assertFalse(strategy.shouldLampBeEnabled(10, time));
		assertFalse(strategy.shouldLampBeEnabled(11, time));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldLampBeEnabledWhenWrongLampNumber() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		strategy.shouldLampBeEnabled(12, time);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldLampBeEnabledWhenNonPositiveLampNumber() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		strategy.shouldLampBeEnabled(-4, time);
	}
}
