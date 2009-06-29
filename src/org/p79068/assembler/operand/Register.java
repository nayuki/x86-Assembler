package org.p79068.assembler.operand;


/**
 * A register operand.
 */
public abstract class Register extends Operand {
	
	private final int registerNumber;
	
	
	
	Register(int registerNumber) {
		this.registerNumber = registerNumber;
	}
	
	
	
	public int getRegisterNumber() {
		return registerNumber;
	}
	
}