package com.ubs.opsit.interviews.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;
import com.ubs.opsit.interviews.logic.MultipleRowClockRowSettingTimeStrategy;

@RunWith(MockitoJUnitRunner.class)  
public class MultipleRowClockTest {

	MultipleRowClock clock;
	
	@Mock
	MultipleRowClockRow rowMock;
	
	@Mock
	MultipleRowClockTime timeMock;
	
	@Test
	public void testHashCode() {
		int numberOfRows = MRCGenerator.generateNumberOfRows();
		int[] sizeOfRows = MRCGenerator.generateSizesOfRows(numberOfRows);
		MultipleRowClockRowSettingTimeStrategy[] strategies = MRCGenerator.generateSettingTimeStrategies(numberOfRows);
		MultipleRowClock testedClock1 = MRCGenerator.generateClock(numberOfRows, sizeOfRows, strategies);
		MultipleRowClock testedClock2 = MRCGenerator.generateClock(numberOfRows, sizeOfRows, strategies);
		assertEquals(testedClock1.hashCode(), testedClock2.hashCode());
	}
	
	@Test
	public void testHashCodeWhenDifferentNumberOfRows() {
		int numberOfRows1 = 10;
		int numberOfRows2 = 5;
		MultipleRowClock testedClock1 = MRCGenerator.generateClock(numberOfRows1, MRCGenerator.generateSizesOfRows(numberOfRows1), 
				MRCGenerator.generateSettingTimeStrategies(numberOfRows1));
		MultipleRowClock testedClock2 = MRCGenerator.generateClock(numberOfRows2, MRCGenerator.generateSizesOfRows(numberOfRows2), 
				MRCGenerator.generateSettingTimeStrategies(numberOfRows2));
		assertFalse(testedClock1.hashCode()==testedClock2.hashCode());
	}

	@Test
	public void testGetTime() {
		int numberOfRows = MRCGenerator.generateNumberOfRows();
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(numberOfRows);
		for (int i = 0; i < numberOfRows; i++) {
			builder = builder.row(rowMock);
		}
		clock = builder.build();
		for (int i = 0; i < numberOfRows; i++) {
			when(rowMock.getState()).thenReturn("stateOfRow");
		}
		StringBuilder expectedTime = new StringBuilder();
		for (int row = 0; row < numberOfRows; row++) {
			expectedTime.append("stateOfRow");
			if (row != numberOfRows-1) {
				expectedTime.append("\r\n");
			}
		}
		assertEquals(expectedTime.toString(), clock.getTime());
	}

	@Test
	public void testSetTime() {
		int numberOfRows = MRCGenerator.generateNumberOfRows();
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(numberOfRows);
		for (int i = 0; i < numberOfRows; i++) {
			builder = builder.row(rowMock);
		}
		clock = builder.build();
		clock.setTime(timeMock);
		verify( rowMock, times(numberOfRows)).setTime(timeMock);
	}

	@Test
	public void testClone() {
		MultipleRowClock testedClock = MRCGenerator.generateClock();
		MultipleRowClock clonedClock = (MultipleRowClock) testedClock.clone();
		assertTrue(testedClock != clonedClock);
		assertEquals(testedClock, clonedClock);
	}

	@Test
	public void testEquals() {
		int numberOfRows = MRCGenerator.generateNumberOfRows();
		int[] sizeOfRows = MRCGenerator.generateSizesOfRows(numberOfRows);
		MultipleRowClockRowSettingTimeStrategy[] strategies = MRCGenerator.generateSettingTimeStrategies(numberOfRows);
		MultipleRowClock testedClock1 = MRCGenerator.generateClock(numberOfRows, sizeOfRows, strategies);
		MultipleRowClock testedClock2 = MRCGenerator.generateClock(numberOfRows, sizeOfRows, strategies);
		assertEquals(testedClock1,testedClock2);
	}

	@Test
	public void testEqualsWhenDifferentNumberOfRows() {
		int numberOfRows1 = 10;
		int numberOfRows2 = 5;
		MultipleRowClock testedClock1 = MRCGenerator.generateClock(numberOfRows1, MRCGenerator.generateSizesOfRows(numberOfRows1), 
				MRCGenerator.generateSettingTimeStrategies(numberOfRows1));
		MultipleRowClock testedClock2 = MRCGenerator.generateClock(numberOfRows2, MRCGenerator.generateSizesOfRows(numberOfRows2), 
				MRCGenerator.generateSettingTimeStrategies(numberOfRows2));
		assertFalse(testedClock1.equals(testedClock2));
	}

}
