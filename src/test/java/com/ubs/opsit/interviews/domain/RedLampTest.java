package com.ubs.opsit.interviews.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ubs.opsit.interviews.domain.RedLamp;

public class RedLampTest {

	@Test
	public void testGetLightWhenOn() {
		char expectedLight = 'R';
		
		RedLamp testedLamp = new RedLamp();
		assertEquals(expectedLight, testedLamp.getLightWhenOn());
	}

}
