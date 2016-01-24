package com.ubs.opsit.interviews;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloWorldImplTest {

	@Test
	public void testSayHelloAndDayOfTheWeek() {
		assertEquals("Hello World on Monday", new HelloWorldImpl().sayHelloAndDayOfTheWeek("25-01-2016"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSayHelloAndDayOfTheWeekIncorrectInputDate() {
		new HelloWorldImpl().sayHelloAndDayOfTheWeek("2016-01-25");
	}

}
