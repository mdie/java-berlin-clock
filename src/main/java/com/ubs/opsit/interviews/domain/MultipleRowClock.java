package com.ubs.opsit.interviews.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;

/**
 * @author mdie
 *
 * (mutable) class containing structure of MultipleRowClock (a clock with at least 2 rows)
 * 
 * example: BerlinClock (has 5 rows)
 *
 */
public class MultipleRowClock implements Cloneable {
	
	private MultipleRowClockRow[] rows;
	
	private MultipleRowClock(int numberOfRows) {
		this.rows = new MultipleRowClockRow[numberOfRows];
	}

	private void addRow(MultipleRowClockRow newRow, int rowNumber) {
		this.rows[rowNumber] = newRow;
	}
	
	/**
	 * @return the state of all rows of the clock
	 */
	public String getTime() {

		StringBuilder result = new StringBuilder();
		for (int row = 0; row < rows.length; row++) {
			result.append(rows[row].getState());
			if (row != rows.length-1) {
				result.append("\r\n");
			}
		}
		
		return result.toString();
	}
	
	/**
	 * enables/disables lamps in clock for given time
	 */
	public void setTime(MultipleRowClockTime time) {
		for (MultipleRowClockRow row: rows) {
			row.setTime(time);
		}
	}
	
	
	@Override
	public Object clone() {
		MultipleRowClock clone = new MultipleRowClock(rows.length);
		for (int i=0; i<rows.length;i++) {
			clone.rows[i] = (MultipleRowClockRow) rows[i].clone();
		}
		return clone; 
	}	
	
	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(rows, ((MultipleRowClock)obj).rows).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(rows).hashCode();
	}

	/**
	 * @author mdie
	 *
	 * a Builder for MultipleRowClock (e.g. BerlinClock)
	 * 
	 * usage e.g.: new MultipleRowClockBuilder(3).row(firstRow).row(secondRow).row(thirdRow).build()
	 */
	public static class MultipleRowClockBuilder {
		
		private MultipleRowClock multipleRowClock;
		private int numberOfRows;
		private int rowCounter;
		
		public MultipleRowClockBuilder(int numberOfRows) {
			if (numberOfRows < 2) {
				throw new IllegalArgumentException(String.format("The MultipleRowClock should have at least 2 rows. Given %d rows is too less.",numberOfRows));
			}
			this.numberOfRows = numberOfRows;
			this.multipleRowClock = new MultipleRowClock(numberOfRows);
		}
		
		/**
		 * add a new row to the clock
		 */
		public MultipleRowClockBuilder row(MultipleRowClockRow newRow) {
			
			if (rowCounter == numberOfRows) {
				throw new IllegalStateException(String.format("You cannot add new row. The clock (size=%d) is full.",numberOfRows));
			}
			this.multipleRowClock.addRow(newRow, rowCounter++);
			
			return this;
		}
		
		public MultipleRowClock build() {
			if (rowCounter != numberOfRows) {
				throw new MultipleRowClockBuilderNotReadyForBuildingException(numberOfRows,rowCounter);
			}
			return this.multipleRowClock;
		}
	}

	static class MultipleRowClockBuilderNotReadyForBuildingException extends IllegalStateException {
		MultipleRowClockBuilderNotReadyForBuildingException(int numberOfRows, int rowCounter) {
			super(String.format("Clock is not finished. You have still to add %d rows.",numberOfRows-rowCounter));
		}
	}
	
}
