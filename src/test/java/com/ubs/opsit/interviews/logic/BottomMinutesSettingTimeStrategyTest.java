package com.ubs.opsit.interviews.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

public class BottomMinutesSettingTimeStrategyTest {
	
	private BottomMinutesSettingTimeStrategy strategy = new BottomMinutesSettingTimeStrategy();

	@Test
	public void testShouldLampBeEnabledJustBeforeMidnight() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
		assertTrue(strategy.shouldLampBeEnabled(2, time));
		assertTrue(strategy.shouldLampBeEnabled(3, time));
		assertTrue(strategy.shouldLampBeEnabled(4, time));
	}

	@Test
	public void testShouldLampBeEnabledMidnight24() {
		MultipleRowClockTime time = new MultipleRowClockTime("24:00:00");
		assertFalse(strategy.shouldLampBeEnabled(1, time));
		assertFalse(strategy.shouldLampBeEnabled(2, time));
		assertFalse(strategy.shouldLampBeEnabled(3, time));
		assertFalse(strategy.shouldLampBeEnabled(4, time));
	}

	@Test
	public void testShouldLampBeEnabledJustAfterMidnight() {
		MultipleRowClockTime time = new MultipleRowClockTime("00:04:00");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
		assertTrue(strategy.shouldLampBeEnabled(2, time));
		assertTrue(strategy.shouldLampBeEnabled(3, time));
		assertTrue(strategy.shouldLampBeEnabled(4, time));
	}

	@Test
	public void testShouldLampBeEnabledEarlyMornig() {
		MultipleRowClockTime time = new MultipleRowClockTime("07:46:03");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
		assertFalse(strategy.shouldLampBeEnabled(2, time));
		assertFalse(strategy.shouldLampBeEnabled(3, time));
		assertFalse(strategy.shouldLampBeEnabled(4, time));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldLampBeEnabledWhenWrongLampNumber() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		strategy.shouldLampBeEnabled(10, time);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldLampBeEnabledWhenNonPositiveLampNumber() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		strategy.shouldLampBeEnabled(0, time);
	}
}
