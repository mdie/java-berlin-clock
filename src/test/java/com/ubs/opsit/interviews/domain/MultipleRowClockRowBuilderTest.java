package com.ubs.opsit.interviews.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.ReflectionUtils;
import org.junit.Test;

import com.ubs.opsit.interviews.logic.MultipleRowClockRowSettingTimeStrategy;

public class MultipleRowClockRowBuilderTest {

	private MultipleRowClockRowSettingTimeStrategy settingTimeStrategy = MRCGenerator.generateStrategy();
	
	@Test
	public void testMultipleRowClockRowBuilder() throws IllegalAccessException {
		int sizeOfRow = 2;
		MultipleRowClockRow.MultipleRowClockRowBuilder builder = 
				new MultipleRowClockRow.MultipleRowClockRowBuilder(sizeOfRow, settingTimeStrategy);
		
		assertEquals(sizeOfRow,ReflectionUtils.getValueIncludingSuperclasses("sizeOfRow", builder));
		assertEquals(0,ReflectionUtils.getValueIncludingSuperclasses("lampCounter", builder));
		assertEquals(settingTimeStrategy,ReflectionUtils.getValueIncludingSuperclasses("settingTimeStrategy", 
				ReflectionUtils.getValueIncludingSuperclasses("multipleRowClockRowClockRow", builder)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMultipleRowClockRowBuilderWrongSize() {
		MultipleRowClockRow.MultipleRowClockRowBuilder builder = new MultipleRowClockRow.MultipleRowClockRowBuilder(0, settingTimeStrategy);
	}

	@Test
	public void testLamps() throws IllegalAccessException {
		int sizeOfRow = 5;
		Lamp[] addedLamps = new Lamp[]{MRCGenerator.generateLamp(), MRCGenerator.generateLamp(), MRCGenerator.generateLamp(), 
				MRCGenerator.generateLamp(), MRCGenerator.generateLamp()};
		MultipleRowClockRow.MultipleRowClockRowBuilder builder = new MultipleRowClockRow.MultipleRowClockRowBuilder(sizeOfRow, settingTimeStrategy);
		builder.lamps(MRCGenerator.generateLamp(),3);
		assertEquals(3,ReflectionUtils.getValueIncludingSuperclasses("lampCounter", builder));
		builder.lamps(MRCGenerator.generateLamp(),2);
		assertEquals(5,ReflectionUtils.getValueIncludingSuperclasses("lampCounter", builder));
		assertArrayEquals(addedLamps, getLampsFromBuilder(builder));
	}

	@Test
	public void testLamp() throws IllegalAccessException {
		int sizeOfRow = 2;
		Lamp[] addedLamps = new Lamp[sizeOfRow];
		addedLamps[0] = MRCGenerator.generateLamp();
		MultipleRowClockRow.MultipleRowClockRowBuilder builder = new MultipleRowClockRow.MultipleRowClockRowBuilder(sizeOfRow, settingTimeStrategy);
		builder.lamp(addedLamps[0]);
		assertEquals(1,ReflectionUtils.getValueIncludingSuperclasses("lampCounter", builder));
		assertArrayEquals(addedLamps, getLampsFromBuilder(builder));
	}

	@Test(expected = IllegalStateException.class)
	public void testLampWhenFull() {
		MultipleRowClockRow.MultipleRowClockRowBuilder builder = new MultipleRowClockRow.MultipleRowClockRowBuilder(1, settingTimeStrategy);
		builder.lamp(MRCGenerator.generateLamp()).lamp(MRCGenerator.generateLamp());
	}
	
	
	@Test
	public void testBuild() throws IllegalAccessException {
		int sizeOfRow = 2;
		Lamp[] addedLamps = new Lamp[sizeOfRow];
		addedLamps[0] = MRCGenerator.generateLamp();
		addedLamps[1] = MRCGenerator.generateLamp();
		MultipleRowClockRow.MultipleRowClockRowBuilder builder = new MultipleRowClockRow.MultipleRowClockRowBuilder(sizeOfRow, settingTimeStrategy);
		MultipleRowClockRow clockRow = builder.lamp(addedLamps[0]).lamp(addedLamps[1]).build();
		assertEquals(settingTimeStrategy,ReflectionUtils.getValueIncludingSuperclasses("settingTimeStrategy", clockRow));
		assertArrayEquals(addedLamps, (Lamp[])ReflectionUtils.getValueIncludingSuperclasses("lamps", clockRow));
	}

	@Test(expected = MultipleRowClockRow.MultipleRowClockRowBuilderNotReadyForBuildingException.class)
	public void testBuildTooEarly() {
		MultipleRowClockRow.MultipleRowClockRowBuilder builder = new MultipleRowClockRow.MultipleRowClockRowBuilder(3, settingTimeStrategy);
		builder.lamp(MRCGenerator.generateLamp()).lamp(MRCGenerator.generateLamp()).build();
		
	}
	
	@Test
	public void testBuildTooEarlyVerifyMessage() {
		MultipleRowClockRow.MultipleRowClockRowBuilder builder = new MultipleRowClockRow.MultipleRowClockRowBuilder(5, settingTimeStrategy);
		try {
			builder.lamp(MRCGenerator.generateLamp()).lamp(MRCGenerator.generateLamp()).build();
		}
		catch (MultipleRowClockRow.MultipleRowClockRowBuilderNotReadyForBuildingException e) {
			assertTrue(StringUtils.contains(e.getMessage(), " 3 lamps"));
		}
		
	}
	
	private Lamp[] getLampsFromBuilder(MultipleRowClockRow.MultipleRowClockRowBuilder builder) throws IllegalAccessException {
		return (Lamp[]) ReflectionUtils.getValueIncludingSuperclasses("lamps", 
				ReflectionUtils.getValueIncludingSuperclasses("multipleRowClockRowClockRow", builder));
	}
	
}
