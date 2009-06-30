package org.p79068.assembler.generator;

import org.p79068.assembler.operand.Operand;


final class LiteralOperandPattern extends OperandPattern {
	
	private Operand literal;
	
	
	
	public LiteralOperandPattern(Operand literal) {
		super(literal.toString());
		this.literal = literal;
	}
	
	
	
	@Override
	public boolean matches(Operand operand) {
		return operand.equals(literal);
	}
	
}
