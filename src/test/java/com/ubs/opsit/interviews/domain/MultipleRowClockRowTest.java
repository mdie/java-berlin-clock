package com.ubs.opsit.interviews.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.ReflectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;
import com.ubs.opsit.interviews.logic.MultipleRowClockRowSettingTimeStrategy;

@RunWith(MockitoJUnitRunner.class)  
public class MultipleRowClockRowTest {

	MultipleRowClockRow clockRow;
	
	@Mock
	Lamp lampMock;
	
	@Mock
	MultipleRowClockRowSettingTimeStrategy strategyMock;
	
	@Mock
	MultipleRowClockTime timeMock;
	
	private MultipleRowClockRowSettingTimeStrategy settingTimeStrategy1 = MRCGenerator.generateStrategy();
	private MultipleRowClockRowSettingTimeStrategy settingTimeStrategy2 = MRCGenerator.generateStrategy();
	
	
	@Test
	public void testHashCode() {
		int sizeOfRow = MRCGenerator.generateSizeOfRow();
		MultipleRowClockRow testedRow1 = MRCGenerator.generateClockRow(sizeOfRow, settingTimeStrategy1);
		MultipleRowClockRow testedRow2 = MRCGenerator.generateClockRow(sizeOfRow, settingTimeStrategy1);
		assertEquals(testedRow1.hashCode(), testedRow2.hashCode());
	}
	
	@Test
	public void testHashCodeWhenDifferentStrategies() {
		int sizeOfRow = MRCGenerator.generateSizeOfRow();
		MultipleRowClockRow testedRow1 = MRCGenerator.generateClockRow(sizeOfRow, settingTimeStrategy1);
		MultipleRowClockRow testedRow2 = MRCGenerator.generateClockRow(sizeOfRow, settingTimeStrategy2);
		assertFalse(testedRow1.hashCode()==testedRow2.hashCode());
	}

	@Test
	public void testHashCodeWhenDifferentSizeOfRow() {
		MultipleRowClockRow testedRow1 = MRCGenerator.generateClockRow(11, settingTimeStrategy2);
		MultipleRowClockRow testedRow2 = MRCGenerator.generateClockRow(9, settingTimeStrategy2);
		assertFalse(testedRow1.hashCode()==testedRow2.hashCode());
	}
	
	@Test
	public void testGetState() {
		int sizeOfRow = MRCGenerator.generateSizeOfRow();
		for (int i = 0; i < sizeOfRow; i++) {
			when(lampMock.clone()).thenReturn(lampMock);
		}
		clockRow = new MultipleRowClockRow.MultipleRowClockRowBuilder(sizeOfRow, settingTimeStrategy1).lamps(lampMock, sizeOfRow).build();
		for (int i = 0; i < sizeOfRow; i++) {
			when(lampMock.getState()).thenReturn('A');
		}
		assertEquals(StringUtils.rightPad("", sizeOfRow, 'A'), clockRow.getState());
	}

	@Test
	public void testSetTime() throws IllegalAccessException {
		int sizeOfRow = MRCGenerator.generateSizeOfRow();
		clockRow = MRCGenerator.generateClockRow(sizeOfRow, strategyMock);
		for (int i = 0; i < sizeOfRow; i++) {
			when(strategyMock.shouldLampBeEnabled(i+1, timeMock)).thenReturn(i%2==0);
		}
		clockRow.setTime(timeMock);
		Lamp[] lamps = (Lamp[])ReflectionUtils.getValueIncludingSuperclasses("lamps", clockRow);
		for (int i = 0; i < sizeOfRow; i++) {
			assertEquals(i%2==0, ReflectionUtils.getValueIncludingSuperclasses("enabled", lamps[i]));
		}
	}

	@Test
	public void testClone() {
		MultipleRowClockRow testedRow = MRCGenerator.generateClockRow();
		MultipleRowClockRow clonedRow = (MultipleRowClockRow) testedRow.clone();
		assertTrue(testedRow != clonedRow);
		assertEquals(testedRow, clonedRow);
	}

	@Test
	public void testEquals() {
		int sizeOfRow = MRCGenerator.generateSizeOfRow();
		MultipleRowClockRow testedRow1 = MRCGenerator.generateClockRow(sizeOfRow, settingTimeStrategy1);
		MultipleRowClockRow testedRow2 = MRCGenerator.generateClockRow(sizeOfRow, settingTimeStrategy1);
		assertEquals(testedRow1,testedRow2);
	}


	@Test
	public void testEqualsWhenDifferentStrategies() {
		int sizeOfRow = MRCGenerator.generateSizeOfRow();
		MultipleRowClockRow testedRow1 = MRCGenerator.generateClockRow(sizeOfRow, settingTimeStrategy1);
		MultipleRowClockRow testedRow2 = MRCGenerator.generateClockRow(sizeOfRow, settingTimeStrategy2);
		assertFalse(testedRow1.equals(testedRow2));
	}

	@Test
	public void testEqualsWhenDifferentSizeOfRow() {
		MultipleRowClockRow testedRow1 = MRCGenerator.generateClockRow(11, settingTimeStrategy2);
		MultipleRowClockRow testedRow2 = MRCGenerator.generateClockRow(9, settingTimeStrategy2);
		assertFalse(testedRow1.equals(testedRow2));
	}

}
