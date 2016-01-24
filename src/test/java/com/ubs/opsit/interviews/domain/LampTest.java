package com.ubs.opsit.interviews.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.codehaus.plexus.util.ReflectionUtils;
import org.junit.Test;

public class LampTest {

	@Test
	public void testGetStateWhenEnabled() {
		Lamp testedLamp = MRCGenerator.generateLamp();
		assertEquals(MRCGenerator.expectedLight, testedLamp.enable().getState());
	}

	@Test
	public void testGetStateWhenDisabled() {
		Lamp testedLamp = MRCGenerator.generateLamp();
		assertEquals('O', testedLamp.disable().getState());
	}

	@Test
	public void testEnable() throws IllegalAccessException {
		Lamp testedLamp = MRCGenerator.generateLamp();
		Lamp enabledLamp = testedLamp.enable();
		assertTrue(testedLamp != enabledLamp);
		assertEquals(false, ReflectionUtils.getValueIncludingSuperclasses("enabled", testedLamp));
		assertEquals(true, ReflectionUtils.getValueIncludingSuperclasses("enabled", enabledLamp));
	}

	@Test
	public void testDisable() throws IllegalAccessException {
		Lamp testedLamp = MRCGenerator.generateLamp();
		Lamp disabledLamp = testedLamp.disable();
		assertTrue(testedLamp != disabledLamp);
		assertEquals(false, ReflectionUtils.getValueIncludingSuperclasses("enabled", disabledLamp));
	}

	@Test
	public void testGetLightWhenOn() {
		Lamp testedLamp = MRCGenerator.generateLamp();
		assertEquals(MRCGenerator.expectedLight, testedLamp.getLightWhenOn());
	}

	@Test
	public void testClone() {
		Lamp testedLamp = MRCGenerator.generateLamp();
		Lamp clonedLamp = (Lamp) testedLamp.clone();
		assertTrue(testedLamp != clonedLamp);
		assertEquals(testedLamp, clonedLamp);
	}

	@Test
	public void testEquals() {
		Lamp testedLamp = MRCGenerator.generateLamp();
		Lamp testedLamp2 = MRCGenerator.generateLamp();
		assertEquals(testedLamp, testedLamp2);
	}
	
	@Test
	public void testHashCode() {
		Lamp testedLamp = MRCGenerator.generateLamp();
		Lamp testedLamp2 = MRCGenerator.generateLamp();
		assertEquals(testedLamp.hashCode(), testedLamp2.hashCode());
	}
	
	@Test
	public void testEqualsWhenDifferentImplementations() {
		
		Lamp testedLampA = new Lamp() {
			@Override
			protected char getLightWhenOn() {
				return 'A';
			}
		};
		Lamp testedLampB = new Lamp() {
			@Override
			protected char getLightWhenOn() {
				return 'B';
			}
		};
		assertFalse(testedLampA.equals(testedLampB));
	}
	
	@Test
	public void testHashCodeWhenDifferentImplementations() {
		
		Lamp testedLampA = new Lamp() {
			@Override
			protected char getLightWhenOn() {
				return 'A';
			}
		};
		Lamp testedLampB = new Lamp() {
			@Override
			protected char getLightWhenOn() {
				return 'B';
			}
		};
		assertFalse(testedLampA.hashCode()==testedLampB.hashCode());
	}
	
}
