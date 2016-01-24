package com.ubs.opsit.interviews.logic;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

/**
 * @author mdie
 *
 * strategy of setting time (enabling lamps in row) for Seconds (1th) row of BerlonClock
 */
public class SecondsSettingTimeStrategy implements MultipleRowClockRowSettingTimeStrategy {

	@Override
	public boolean shouldLampBeEnabled(int lampNumber, MultipleRowClockTime time) {
		if (lampNumber != 1) {
			throw new IllegalArgumentException("This strategy serves only lamps with number 1");
		}
		return time.getSeconds() % 2 == 0;
	}

}
