package org.p79068.assembler.operand;


/**
 * A register operand.
 */
public abstract class Register extends Operand {
	
	private final int registerNumber;
	
	private final String name;
	
	
	
	Register(String name, int registerNumber) {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
		this.registerNumber = registerNumber;
	}
	
	
	
	public int getRegisterNumber() {
		return registerNumber;
	}
	
	
	/**
	 * Returns a string representation of this register. The format is subjected to change.
	 * @return a string representation of this register
	 */
	public String toString() {
		return name;
	}
	
}
