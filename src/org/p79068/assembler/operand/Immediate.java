package org.p79068.assembler.operand;

import java.util.Map;


/**
 * An immediate operand.
 */
public abstract class Immediate extends Operand {
	
	/**
	 * Constructs an immediate operand.
	 */
	Immediate() {}
	
	
	/**
	 * Returns the value of this immediate operand, given the specified label offset mapping.
	 * @param labelOffsets the mapping of label names to offsets
	 * @return the value of this immediate operand
	 */
	public abstract ImmediateValue getValue(Map<String,Integer> labelOffsets);
	
}
