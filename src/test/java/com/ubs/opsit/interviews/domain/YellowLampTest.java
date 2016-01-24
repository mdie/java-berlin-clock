package com.ubs.opsit.interviews.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ubs.opsit.interviews.domain.YellowLamp;

public class YellowLampTest {

	@Test
	public void testGetLightWhenOn() {
		char expectedLight = 'Y';
		
		YellowLamp testedLamp = new YellowLamp();
		assertEquals(expectedLight, testedLamp.getLightWhenOn());
	}

}
