package org.p79068.assembler.generator;



final class ModRM extends InstructionOption {
	
	public final int rmParameterIndex;
	
	public final int regOpcodeParameterIndex;
	
	
	
	public ModRM(int rmParamIndex, int regOpParamIndex) {
		if (rmParamIndex < 0 || rmParamIndex >= 10)
			throw new IllegalArgumentException("Invalid parameter index");
		if (regOpParamIndex < 0 || regOpParamIndex >= 18)
			throw new IllegalArgumentException("Invalid parameter index or constant");
		rmParameterIndex = rmParamIndex;
		regOpcodeParameterIndex = regOpParamIndex;
	}
	
}