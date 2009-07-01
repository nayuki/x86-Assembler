package org.p79068.assembler.operand;

import org.p79068.assembler.Program;


/**
 * An immediate value.
 */
public abstract class Immediate extends Operand {
	
	public abstract ImmediateValue getValue(Program program);
	
}
