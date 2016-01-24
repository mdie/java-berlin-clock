package com.ubs.opsit.interviews.logic;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

/**
 * @author mdie
 *
 *	determine if a lamp (with given number in row of MultipleRowClockTime) should be enabled for given time
 */
public interface MultipleRowClockRowSettingTimeStrategy {

	boolean shouldLampBeEnabled(int lampNumber, MultipleRowClockTime time);
	
}
