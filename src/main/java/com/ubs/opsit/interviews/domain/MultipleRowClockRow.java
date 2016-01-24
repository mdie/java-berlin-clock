package com.ubs.opsit.interviews.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;
import com.ubs.opsit.interviews.logic.MultipleRowClockRowSettingTimeStrategy;

/**
 * @author mdie
 * (mutable) class containing structure of row of the MultipleRowClock (a row must contain at least 1 lamp)
 * 
 * example: 4th row BerlinClock (has 11 lamps)
 */
public class MultipleRowClockRow implements Cloneable {

	private Lamp[] lamps;
	private MultipleRowClockRowSettingTimeStrategy settingTimeStrategy;
	
	private MultipleRowClockRow(int numberOfLamps, MultipleRowClockRowSettingTimeStrategy settingTimeStrategy) {
		this.lamps = new Lamp[numberOfLamps];
		this.settingTimeStrategy = settingTimeStrategy;
	}

	private void addLamp(Lamp newLamp, int column) {
		this.lamps[column] = newLamp;
	}
	
	/**
	 * @return the state of row (all lamps)
	 */
	public String getState() {
		
		StringBuilder result = new StringBuilder();
		for (Lamp lamp: lamps) {
			result.append(lamp.getState());
		}
		
		return result.toString();
	}
	
	/**
	 * enables/disables lamps in the row of the clock for given time
	 */
	public void setTime(MultipleRowClockTime time) {
		for (int lampNumber=1; lampNumber<=lamps.length; lampNumber++) {
			if (settingTimeStrategy.shouldLampBeEnabled(lampNumber, time)) {
				lamps[lampNumber-1] = lamps[lampNumber-1].enable();
			}
			else {
				lamps[lampNumber-1] = lamps[lampNumber-1].disable();
			}
		}		
	}
	
	@Override
	public Object clone() {
		MultipleRowClockRow clone = new MultipleRowClockRow(lamps.length, settingTimeStrategy);
		for (int i=0; i<lamps.length;i++) {
			clone.lamps[i] = (Lamp) lamps[i].clone();
		}
		return clone; 
	}
	
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(lamps, ((MultipleRowClockRow)obj).lamps).
				append(settingTimeStrategy, ((MultipleRowClockRow)obj).settingTimeStrategy).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(lamps).append(settingTimeStrategy).hashCode();
	}

	/**
	 * @author mdie
	 *
	 * a Builder for MultipleRowClockRow (e.g. a row of BerlinClock)
	 * 
	 * 	usage e.g.:  new MultipleRowClockRow.MultipleRowClockRowBuilder(2, multipleRowClockRowSettingTimeStrategy).lamp(lamp1).lamp(lamp2).build();
	 * 
	 */
	public static class MultipleRowClockRowBuilder {
		
		private MultipleRowClockRow multipleRowClockRowClockRow;
		private int sizeOfRow;
		private int lampCounter;
		
		public MultipleRowClockRowBuilder(int sizeOfRow, MultipleRowClockRowSettingTimeStrategy settingTimeStrategy) {
			if (sizeOfRow < 1) {
				throw new IllegalArgumentException(String.format("The row of MultipleRowClock should have at least 1 lamp. Given %d lamps is too less.",sizeOfRow));
			}
			this.sizeOfRow = sizeOfRow;
			this.multipleRowClockRowClockRow = new MultipleRowClockRow(sizeOfRow, settingTimeStrategy);
		}
		
		/**
		 *  add N (numberOfNewLamps) new lamps to the row
		 */
		public MultipleRowClockRowBuilder lamps(Lamp newLamp, int numberOfNewLamps) {
			for (int i= 0; i < numberOfNewLamps; i++) {
				this.lamp((Lamp) newLamp.clone());
			}
			return this;
		}
		
		/**
		 * add a new lamp to the row
		 */
		public MultipleRowClockRowBuilder lamp(Lamp newLamp) {
			
			if (lampCounter == sizeOfRow) {
				throw new IllegalStateException(String.format("You cannot add lamp. The row (size=%d) is full.",sizeOfRow));
			}
			this.multipleRowClockRowClockRow.addLamp(newLamp, lampCounter++);
			
			return this;
		}
		
		public MultipleRowClockRow build() {
			
			if (lampCounter != sizeOfRow) {
				throw new MultipleRowClockRowBuilderNotReadyForBuildingException(sizeOfRow,lampCounter);
			}
			return this.multipleRowClockRowClockRow;
		}
		
	}
	
	static class MultipleRowClockRowBuilderNotReadyForBuildingException extends IllegalStateException {
		MultipleRowClockRowBuilderNotReadyForBuildingException(int sizeOfRow, int lampCounter) {
			super(String.format("Row of MultipleRowClock is not finished. You have still to add %d lamps.",sizeOfRow-lampCounter));
		}
	}
}
