package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.domain.MultipleRowClock;
import com.ubs.opsit.interviews.input.BerlinClockPrototype;
import com.ubs.opsit.interviews.input.MultipleRowClockTime;

/**
 * @author mdie
 * 
 * converts given time (MultipleRowClockTime) into BerlinClock format
 */
public class BerlinClockConverter implements TimeConverter  {

	public String convertTime(String aTime) {
		
		MultipleRowClock berlinClock = BerlinClockPrototype.getBerlinClock();
		MultipleRowClockTime time = new MultipleRowClockTime(aTime);
		berlinClock.setTime(time);
		
		return berlinClock.getTime();
	}
}
