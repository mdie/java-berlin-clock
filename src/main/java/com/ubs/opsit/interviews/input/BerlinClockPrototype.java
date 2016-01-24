package com.ubs.opsit.interviews.input;

import com.ubs.opsit.interviews.domain.MultipleRowClock;
import com.ubs.opsit.interviews.domain.MultipleRowClockRow;
import com.ubs.opsit.interviews.domain.RedLamp;
import com.ubs.opsit.interviews.domain.YellowLamp;
import com.ubs.opsit.interviews.domain.MultipleRowClock.MultipleRowClockBuilder;
import com.ubs.opsit.interviews.logic.BottomHoursSettingTimeStrategy;
import com.ubs.opsit.interviews.logic.BottomMinutesSettingTimeStrategy;
import com.ubs.opsit.interviews.logic.SecondsSettingTimeStrategy;
import com.ubs.opsit.interviews.logic.TopHoursSettingTimeStrategy;
import com.ubs.opsit.interviews.logic.TopMinutesSettingTimeStrategy;

/**
 * @author mdie
 *
 *	a Prototype Pattern containing already build BerlinClock instance 
 */
public class BerlinClockPrototype {

	private /*final */static MultipleRowClock clockPrototype;
	private final static int NUMBER_OF_ROWS = 5; 
	
	private BerlinClockPrototype() {}
	
	static {
		
		// first row - yellow lamp
		MultipleRowClockRow firstRow =  new MultipleRowClockRow.MultipleRowClockRowBuilder(1, new SecondsSettingTimeStrategy())
				.lamp(new YellowLamp()).build();
		
		// second and third row - 4 red lamps in each one
		MultipleRowClockRow secondRow =  new MultipleRowClockRow.MultipleRowClockRowBuilder(4, new TopHoursSettingTimeStrategy())
				.lamps(new RedLamp(),4).build();
		
		MultipleRowClockRow thirdRow =  new MultipleRowClockRow.MultipleRowClockRowBuilder(4, new BottomHoursSettingTimeStrategy())
				.lamps(new RedLamp(),4).build();
		
		// fourth row - 11 lamps (the 3rd, 6th and 9th lamp are red)
		MultipleRowClockRow fourthRow =  new MultipleRowClockRow.MultipleRowClockRowBuilder(11, new TopMinutesSettingTimeStrategy())
				.lamps(new YellowLamp(),2).lamp(new RedLamp())
				.lamps(new YellowLamp(),2).lamp(new RedLamp()).lamps(new YellowLamp(),2).lamp(new RedLamp()).lamps(new YellowLamp(),2)
				.build();
		
		// fifth row - 4 yellow lamps
		MultipleRowClockRow fifthRow =  new MultipleRowClockRow.MultipleRowClockRowBuilder(4, new BottomMinutesSettingTimeStrategy())
				.lamps(new YellowLamp(),4).build();

		clockPrototype = new MultipleRowClockBuilder(NUMBER_OF_ROWS).row(firstRow).row(secondRow).row(thirdRow).row(fourthRow).row(fifthRow).build();
		
	}
	
	/**
	 * @return clone of prototype (BerlinClock)
	 */
	public static MultipleRowClock getBerlinClock() {
		return (MultipleRowClock) clockPrototype.clone();
	}
	
}
