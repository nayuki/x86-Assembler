package org.p79068.assembler.generator;



final class RegisterInOpcode extends InstructionOption {
	
	public final int parameterIndex;
	
	
	
	public RegisterInOpcode(int parameterIndex) {
		if (parameterIndex < 0)
			throw new IllegalArgumentException("Invalid parameter index");
		this.parameterIndex = parameterIndex;
	}
	
}