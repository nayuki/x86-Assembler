package org.p79068.assembler.operand;



/**
 * A register operand. Immutable. Each register has a number, which is typically used in the ModR/M byte or the SIB byte.
 */
public abstract class Register extends Operand {
	
	/** The register number. */
	private final int registerNumber;
	
	/** The name, which is for display purposes only. */
	private final String name;
	
	
	
	/**
	 * Constructs a register with the specified name and register number.
	 * @param name the name of the register
	 * @param registerNumber the register number
	 * @throws NullPointerException if the name is {@code null}
	 * @throws IllegalArgumentException if the register number is not in the range [0, 8)
	 */
	Register(String name, int registerNumber) {
		if (name == null)
			throw new NullPointerException();
		if (registerNumber < 0 || registerNumber >= 8)
			throw new IllegalArgumentException("Invalid register number");
		this.name = name;
		this.registerNumber = registerNumber;
	}
	
	
	
	/**
	 * Returns the number of this register, which is an integer from 0 to 7 (inclusive).
	 * @return the number of this register
	 */
	public int getRegisterNumber() {
		return registerNumber;
	}
	
	
	/**
	 * Returns a string representation of this register. The format is subjected to change.
	 * @return a string representation of this register
	 */
	@Override
	public String toString() {
		return name;
	}
	
}
