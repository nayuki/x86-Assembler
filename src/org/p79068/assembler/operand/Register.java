package org.p79068.assembler.operand;



/**
 * A register operand.
 */
public abstract class Register extends Operand {
	
	public static Register parseRegister(String op) {
		Register result = null;
		
		if (result == null) result = Register32.parseOperand(op);
		if (result == null) result = Register16.parseOperand(op);
		if (result == null) result = Register8.parseOperand(op);
		if (result == null) result = SegmentRegister.parseOperand(op);
		
		if (result == null)
			throw new IllegalArgumentException("Illegal operand: " + op);
		
		return result;
		}
	
	
	
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
