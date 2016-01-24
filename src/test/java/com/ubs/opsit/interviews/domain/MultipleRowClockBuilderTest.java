package com.ubs.opsit.interviews.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.ReflectionUtils;
import org.junit.Test;

import com.ubs.opsit.interviews.domain.MultipleRowClock.MultipleRowClockBuilderNotReadyForBuildingException;

public class MultipleRowClockBuilderTest {

	@Test
	public void testMultipleRowClockBuilder() throws IllegalAccessException {
		int numberOfRows = 3;
		MultipleRowClock.MultipleRowClockBuilder builder = 
				new MultipleRowClock.MultipleRowClockBuilder(numberOfRows);
		
		assertEquals(numberOfRows,ReflectionUtils.getValueIncludingSuperclasses("numberOfRows", builder));
		assertEquals(0,ReflectionUtils.getValueIncludingSuperclasses("rowCounter", builder));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultipleRowClockBuilderWrongNumberOfRows() {
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(1);
	}

	@Test
	public void testRow() throws IllegalAccessException {
		int numberOfRows = 2;
		MultipleRowClockRow[] addedRows = new MultipleRowClockRow[numberOfRows];
		addedRows[0] = MRCGenerator.generateClockRow();
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(numberOfRows);
		builder.row(addedRows[0]);
		assertEquals(1,ReflectionUtils.getValueIncludingSuperclasses("rowCounter", builder));
		assertArrayEquals(addedRows, getRowsFromBuilder(builder));
	}

	@Test(expected = IllegalStateException.class)
	public void testRowWhenFull() {
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(2);
		builder.row(MRCGenerator.generateClockRow()).row(MRCGenerator.generateClockRow()).row(MRCGenerator.generateClockRow());
	}
	
	@Test
	public void testBuild() throws IllegalAccessException {
		int numberOfRows = 2;
		MultipleRowClockRow[] addedRows = new MultipleRowClockRow[numberOfRows];
		addedRows[0] = MRCGenerator.generateClockRow();
		addedRows[1] = MRCGenerator.generateClockRow();
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(2);
		MultipleRowClock clock = builder.row(addedRows[0]).row(addedRows[1]).build();
		assertArrayEquals(addedRows, (MultipleRowClockRow[])ReflectionUtils.getValueIncludingSuperclasses("rows", clock));
	}

	@Test(expected = MultipleRowClock.MultipleRowClockBuilderNotReadyForBuildingException.class)
	public void testBuildTooEarly() {
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(2);
		builder.row(MRCGenerator.generateClockRow()).build();
		
	}
	
	@Test
	public void testBuildTooEarlyVerifyMessage() {
		MultipleRowClock.MultipleRowClockBuilder builder = new MultipleRowClock.MultipleRowClockBuilder(3);
		try {
			builder.row(MRCGenerator.generateClockRow()).build();
		}
		catch (MultipleRowClockBuilderNotReadyForBuildingException e) {
			assertTrue(StringUtils.contains(e.getMessage(), " 2 rows"));
		}
		
	}
	
	private MultipleRowClockRow[] getRowsFromBuilder(MultipleRowClock.MultipleRowClockBuilder builder) throws IllegalAccessException {
		return (MultipleRowClockRow[]) ReflectionUtils.getValueIncludingSuperclasses("rows", 
				ReflectionUtils.getValueIncludingSuperclasses("multipleRowClock", builder));
	}
	


}
