package org.p79068.assembler.generator;



/**
 * An option specifing that a register operand is encoded into the last byte of the instruction's opcode.
 */
final class RegisterInOpcode extends InstructionOption {
	
	/**
	 * The index of the operand to encode into the instruction's opcode.
	 */
	public final int operandIndex;
	
	
	
	/**
	 * Constructs a register-in-opcode option with the specified operand index.
	 * @param operandIndex the operand index
	 * @throws IllegalArgumentException if the operand index is negative
	 */
	public RegisterInOpcode(int operandIndex) {
		if (operandIndex < 0)
			throw new IllegalArgumentException("Invalid operand index");
		this.operandIndex = operandIndex;
	}
	
}
