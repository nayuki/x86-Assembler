package org.p79068.assembler.operand;


/**
 * An operand for an instruction. Immutable.
 * @see Immediate
 * @see Register
 * @see Memory32
 */
public class Operand {
	
	public static Operand parseOperand(String op) {
		Operand result = null;
		
		if (result == null) try { result = new ImmediateValue(Integer.parseInt(op)); } catch (NumberFormatException e) {}
		
		if (result == null)
			throw new IllegalArgumentException("Illegal operand: " + op);
		
		return result;
	}
	
}
