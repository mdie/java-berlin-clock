package com.ubs.opsit.interviews.domain;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.math.RandomUtils;

import com.ubs.opsit.interviews.input.MultipleRowClockTime;
import com.ubs.opsit.interviews.logic.MultipleRowClockRowSettingTimeStrategy;

public class MRCGenerator {
	
	static final char expectedLight = 'X';
	
	private MRCGenerator() {}
	
	static Lamp generateLamp() {
		return new Lamp() {
			@Override
			protected char getLightWhenOn() {
				return expectedLight;
			}
		};
	}

	static MultipleRowClockRowSettingTimeStrategy generateStrategy() {
		return new MultipleRowClockRowSettingTimeStrategy() {
			@Override
			public boolean shouldLampBeEnabled(int lampNumber, MultipleRowClockTime time) {
				return lampNumber%(RandomUtils.nextInt()%3) == 0;
			}
		};
	}
	
	static MultipleRowClockRow generateClockRow() {
		MultipleRowClockRowSettingTimeStrategy settingTimeStrategy = generateStrategy(); 
		return generateClockRow(generateSizeOfRow(), settingTimeStrategy);
	}

	static MultipleRowClockRow generateClockRow(int sizeOfRow, MultipleRowClockRowSettingTimeStrategy settingTimeStrategy) {
		return new MultipleRowClockRow.MultipleRowClockRowBuilder(sizeOfRow, settingTimeStrategy).
				lamps(MRCGenerator.generateLamp(), sizeOfRow).build();
	}	
	
	static int generateNumberOfRows() {
		return RandomUtils.nextInt()%100+2;
	}
	
	static int generateSizeOfRow() {
		return RandomUtils.nextInt()%20+1;
	}
	
	static MultipleRowClock generateClock() {
		int numberOfRows = generateNumberOfRows();
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(numberOfRows);
		for (int i = 1; i <= numberOfRows; i++) {
			builder = builder.row(generateClockRow());
		}
		return builder.build();
	}
	
	static MultipleRowClock generateClock(int numberOfRows, int[] sizesOfRows, MultipleRowClockRowSettingTimeStrategy[] strategies) {
		Validate.isTrue(numberOfRows == sizesOfRows.length);
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(numberOfRows);
		for (int i = 1; i <= numberOfRows; i++) {
			builder = builder.row(generateClockRow(sizesOfRows[i-1], strategies[i-1]));
		}
		return builder.build();
	}
	
	static int[] generateSizesOfRows(int numberOfRows) {
		int[] result = new int[numberOfRows];
		for (int i = 0; i < numberOfRows; i++) {
			result[i] = generateSizeOfRow();
		}
		return result;
	}
	
	static MultipleRowClockRowSettingTimeStrategy[] generateSettingTimeStrategies(int numberOfRows) {
		MultipleRowClockRowSettingTimeStrategy[] result = new MultipleRowClockRowSettingTimeStrategy[numberOfRows];
		for (int i = 0; i < numberOfRows; i++) {
			result[i] = generateStrategy();
		}
		return result;
	}
	
}