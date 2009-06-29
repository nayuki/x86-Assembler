package org.p79068.assembler.operand;


/**
 * An immediate literal value.
 */
public class Immediate extends Operand {
	
	/** The constant zero. */
	public static final Immediate ZERO = new Immediate(0);
	
	
	
	private int value;
	
	
	
	public Immediate(int value) {
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
	
	
	public boolean is8Bit() {
		return (value >> 8) == (value >> 31);
	}
	
	
	public boolean is16Bit() {
		return (value >> 16) == (value >> 31);
	}
	
	
	public int getValue() {
		return value;
	}
	
	
	public boolean equals(Object other) {
		if (!(other instanceof Immediate))
			return false;
		else
			return value == ((Immediate)other).value;
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