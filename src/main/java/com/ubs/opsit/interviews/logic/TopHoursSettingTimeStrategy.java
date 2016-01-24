package com.ubs.opsit.interviews.logic;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

/**
 * @author mdie
 * 
 * strategy of setting time (enabling lamps in row) for TopHours (2nd) row of BerlonClock
 */
public class TopHoursSettingTimeStrategy implements MultipleRowClockRowSettingTimeStrategy {

	@Override
	public boolean shouldLampBeEnabled(int lampNumber, MultipleRowClockTime time) {
		if (lampNumber <1 || lampNumber >4) {
			throw new IllegalArgumentException("This strategy serves only lamps with number between 1 and 4");
		}
		return time.getHour()/5 >= lampNumber;
	}

}
