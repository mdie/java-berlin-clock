package com.ubs.opsit.interviews.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BerlinClockPrototypeTest {

	@Test
	public void testGetBerlinClockWheterReturnNewInstance() {
		assertTrue(BerlinClockPrototype.getBerlinClock() != BerlinClockPrototype.getBerlinClock());
		assertEquals(BerlinClockPrototype.getBerlinClock(), BerlinClockPrototype.getBerlinClock());
	}

}
