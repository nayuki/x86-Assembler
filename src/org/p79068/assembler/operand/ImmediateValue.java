package org.p79068.assembler.operand;

import org.p79068.assembler.Program;


/**
 * An immediate literal value.
 */
public class ImmediateValue extends Immediate {

	/** The constant zero. */
	public static final ImmediateValue ZERO = new ImmediateValue(0);
	
	
	
	private int value;
	
	
	
	public ImmediateValue(int value) {
		this.value = value;
	}
	
	
	
	/**
	 * Tests whether this value is zero.
	 * @return {@code true} if this value is zero, {@code false} otherwise
	 */
	public boolean isZero() {
		return value == 0;
	}
	
	
	/**
	 * Tests whether this value is a signed 8-bit number.
	 * @return {@code true} if this value is a signed 8-bit number, {@code false} otherwise
	 */
	public boolean isSigned8Bit() {
		return ((byte)value) == value;
	}
	
	
	/**
	 * Tests whether this value is a signed 16-bit number.
	 * @return {@code true} if this value is a signed 16-bit number, {@code false} otherwise
	 */
	public boolean isSigned16Bit() {
		return ((short)value) == value;
	}
	
	
	public boolean is8Bit() {
		return (value >> 8) == (value >> 31);
	}
	
	
	public boolean is16Bit() {
		return (value >> 16) == (value >> 31);
	}
	
	
	public ImmediateValue getValue(Program program) {
		return this;
	}
	
	
	public int getValue() {
		return value;
	}
	
	
	public boolean equals(Object other) {
		if (!(other instanceof ImmediateValue))
			return false;
		else
			return value == ((ImmediateValue)other).value;
	}
	
	
	public int hashCode() {
		return value;
	}
	
	
	public String toString() {
		return Integer.toString(value);
	}
	
	
	public byte[] to4Bytes() {
		return new byte[]{
			(byte)(value >>>  0),
			(byte)(value >>>  8),
			(byte)(value >>> 16),
			(byte)(value >>> 24)
		};
	}
	
	
	public byte[] to2Bytes() {
		if (!is16Bit())
			throw new IllegalStateException("Not a 16-bit value");
		return new byte[]{
			(byte)(value >>>  0),
			(byte)(value >>>  8)
		};
	}
	
	
	public byte[] to1Byte() {
		if (!is8Bit())
			throw new IllegalStateException("Not an 8-bit value");
		return new byte[]{
			(byte)(value >>>  0)
		};
	}
	
}
