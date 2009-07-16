package org.p79068.assembler.generator;



/**
 * An option that specifies the generation of a ModR/M byte (and possibly a SIB byte). The reg/opcode field is either a register operand or a fixed number.
 */
final class ModRM extends InstructionOption {
	
	/**
	 * The index of the operand for the r/m field.
	 */
	public final int rmOperandIndex;
	
	/**
	 * The reg/opcode field, which is either the index of a register operand or a fixed number. Values in the range [0, 10) are interpreted as operand indexes. Values in the range [10, 18) are interpreted as opcode constants in the range [0, 8).
	 */
	public final int regOpcodeOperandIndex;
	
	
	
	/**
	 * Constructs a ModR/M option with the specified r/m operand index and reg/opcode operand index or opcode.
	 * @param rmOperandIndex the r/m operand index
	 * @param regOpOperandIndex the reg/opcode operand index or opcode
	 * @throws IllegalArgumentException if the r/m operand index or the reg/opcode operand index or opcode is negative
	 * @throws IllegalArgumentException if the reg/opcode operand index or opcode is greater than or equal to 18
	 */
	public ModRM(int rmOperandIndex, int regOpOperandIndex) {
		if (rmOperandIndex < 0 || rmOperandIndex >= 10)
			throw new IllegalArgumentException("Invalid operand index");
		if (regOpOperandIndex < 0 || regOpOperandIndex >= 18)
			throw new IllegalArgumentException("Invalid operand index or constant");
		this.rmOperandIndex = rmOperandIndex;
		this.regOpcodeOperandIndex = regOpOperandIndex;
	}
	
}
