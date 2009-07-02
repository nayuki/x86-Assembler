package org.p79068.assembler.generator;



final class RegisterInOpcode extends InstructionOption {
	
	public final int operandIndex;
	
	
	
	public RegisterInOpcode(int operandIndex) {
		if (operandIndex < 0)
			throw new IllegalArgumentException("Invalid operand index");
		this.operandIndex = operandIndex;
	}
	
}
