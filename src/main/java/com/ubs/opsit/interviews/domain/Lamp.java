package com.ubs.opsit.interviews.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author mdie
 *	
 * an immutable abstract class representing a Lamp, subclass must define the color of light when the lamp is enabled (switched on) 
 */
public abstract class Lamp implements Cloneable {

	private boolean enabled;
	
	public char getState() {
		return enabled?getLightWhenOn():'O';
	}
	
	public Lamp enable() {
		Lamp enabledLamp = (Lamp) this.clone();
		enabledLamp.enabled = true;
		return enabledLamp;
	}

	public Lamp disable() {
		Lamp disabledLamp = (Lamp) this.clone();
		disabledLamp.enabled = false;
		return disabledLamp;
	}

	protected abstract char getLightWhenOn();
	
	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		return new EqualsBuilder().append(enabled, ((Lamp)obj).enabled).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(enabled).append(getClass()).hashCode();
	}
}
