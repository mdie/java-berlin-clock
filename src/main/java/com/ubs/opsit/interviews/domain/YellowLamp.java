package com.ubs.opsit.interviews.domain;

/**
 * @author mdie
 * 
 * class representing a lamp with yellow light
 */
public class YellowLamp extends Lamp {

	@Override
	protected char getLightWhenOn() {
		return 'Y';
	}
}
