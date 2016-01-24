package com.ubs.opsit.interviews.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

public class SecondsSettingTimeStrategyTest {
	
	private SecondsSettingTimeStrategy strategy = new SecondsSettingTimeStrategy();

	@Test
	public void testShouldLampBeEnabledJustBeforeMidnight() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		assertFalse(strategy.shouldLampBeEnabled(1, time));
	}

	@Test
	public void testShouldLampBeEnabledMidnight24() {
		MultipleRowClockTime time = new MultipleRowClockTime("24:00:00");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
	}

	@Test
	public void testShouldLampBeEnabledMidnight00() {
		MultipleRowClockTime time = new MultipleRowClockTime("00:00:00");
		assertTrue(strategy.shouldLampBeEnabled(1, time));
	}

	@Test
	public void testShouldLampBeEnabledEarlyMornig() {
		MultipleRowClockTime time = new MultipleRowClockTime("07:46:03");
		assertFalse(strategy.shouldLampBeEnabled(1, time));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldLampBeEnabledWhenWrongLampNumber() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		strategy.shouldLampBeEnabled(0, time);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldLampBeEnabledWhenNegativeLampNumber() {
		MultipleRowClockTime time = new MultipleRowClockTime("23:59:59");
		strategy.shouldLampBeEnabled(-15, time);
	}
}
