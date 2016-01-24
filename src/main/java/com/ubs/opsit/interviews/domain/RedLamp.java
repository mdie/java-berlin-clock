package com.ubs.opsit.interviews.domain;

/**
 * @author mdie
 * 
 * class representing a lamp with red light
 */
public class RedLamp extends Lamp {

	@Override
	protected char getLightWhenOn() {
		return 'R';
	}
}
