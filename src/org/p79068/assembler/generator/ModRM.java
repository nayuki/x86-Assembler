package org.p79068.assembler.generator;



final class ModRM extends InstructionOption {
	
	public final int rmOperandIndex;
	
	public final int regOpcodeOperandIndex;
	
	
	
	public ModRM(int rmOperandIndex, int regOpOperandIndex) {
		if (rmOperandIndex < 0 || rmOperandIndex >= 10)
			throw new IllegalArgumentException("Invalid operand index");
		if (regOpOperandIndex < 0 || regOpOperandIndex >= 18)
			throw new IllegalArgumentException("Invalid operand index or constant");
		this.rmOperandIndex = rmOperandIndex;
		this.regOpcodeOperandIndex = regOpOperandIndex;
	}
	
}
