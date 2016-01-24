package com.ubs.opsit.interviews.logic;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

/**
 * @author mdie
 * 
 * strategy of setting time (enabling lamps in row) for TopMinutes (fourth) row of BerlonClock 
 */
public class TopMinutesSettingTimeStrategy implements MultipleRowClockRowSettingTimeStrategy {

	@Override
	public boolean shouldLampBeEnabled(int lampNumber, MultipleRowClockTime time) {
		if (lampNumber <1 || lampNumber>11) {
			throw new IllegalArgumentException("This strategy serves only lamps with number between 1 and 11");
		}
		return time.getMinutes()/5>=lampNumber;
	}

}
