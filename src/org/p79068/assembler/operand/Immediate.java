package org.p79068.assembler.operand;

import java.util.Map;


/**
 * An immediate value.
 */
public abstract class Immediate extends Operand {
	
	Immediate() {}
	
	
	public abstract ImmediateValue getValue(Map<String,Integer> labelOffsets);
	
}
