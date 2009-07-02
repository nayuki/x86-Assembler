package org.p79068.assembler.operand;

import org.p79068.assembler.Program;


/**
 * An immediate literal value.
 */
public class ImmediateValue extends Immediate {

	/** The constant zero. */
	public static final ImmediateValue ZERO = new ImmediateValue(0);
	
	
	
	private int value;
	
	
	
	/**
	 * Constructs an immediate value with the specified signed or unsigned 32-bit integer value.
	 * @param value the value
	 */
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
	 * Tests whether this value is a signed 8-bit integer.
	 * @return {@code true} if this value is a signed 8-bit integer, {@code false} otherwise
	 */
	public boolean isSigned8Bit() {
		return ((byte)value) == value;
	}
	
	
	/**
	 * Tests whether this value is a signed 16-bit integer.
	 * @return {@code true} if this value is a signed 16-bit integer, {@code false} otherwise
	 */
	public boolean isSigned16Bit() {
		return ((short)value) == value;
	}
	
	
	/**
	 * Tests whether this value is a signed or unsigned 8-bit integer.
	 * @return {@code true} if this value is a signed or unsigned 8-bit integer, {@code false} otherwise
	 */
	public boolean is8Bit() {
		return value >= -0x80 && value < 0x100;
	}
	
	
	/**
	 * Tests whether this value is a signed or unsigned 16-bit integer.
	 * @return {@code true} if this value is a signed or unsigned 16-bit integer, {@code false} otherwise
	 */
	public boolean is16Bit() {
		return value >= -0x8000 && value < 0x10000;
	}
	
	
	/**
	 * Tests whether this value is a signed or unsigned 32-bit integer.
	 * @return {@code true} if this value is a signed or unsigned 32-bit integer, {@code false} otherwise
	 */
	public ImmediateValue getValue(Program program) {
		return this;
	}
	
	
	/**
	 * Returns the value of this immediate value.
	 * @return the value
	 */
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
	
	
	/**
	 * Returns a string representation of this immediate value. The format is subjected to change.
	 * @return a string representation of this immediate value
	 */
	public String toString() {
		return Integer.toString(value);
	}
	
	
	/**
	 * Returns this value encoded as 4 bytes in little endian.
	 * @return this value encoded as 4 bytes in little endian
	 */
	public byte[] to4Bytes() {
		return new byte[]{
			(byte)(value >>>  0),
			(byte)(value >>>  8),
			(byte)(value >>> 16),
			(byte)(value >>> 24)
		};
	}
	
	
	/**
	 * Returns the low 16 bits of this value encoded as 2 bytes in little endian.
	 * @return the low 16 bits of this value encoded as 2 bytes in little endian
	 */
	public byte[] to2Bytes() {
		if (!is16Bit())
			throw new IllegalStateException("Not a 16-bit integer");
		return new byte[]{
			(byte)(value >>>  0),
			(byte)(value >>>  8)
		};
	}
	
	
	/**
	 * Returns the low 8 bits of this value encoded as 1 byte.
	 * @return the low 8 bits of this value encoded as 1 byte
	 */
	public byte[] to1Byte() {
		if (!is8Bit())
			throw new IllegalStateException("Not an 8-bit integer");
		return new byte[]{
			(byte)(value >>>  0)
		};
	}
	
}
