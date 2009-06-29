package org.p79068.assembler;

import org.p79068.assembler.generator.InstructionPattern;
import org.p79068.assembler.operand.Operand;


public final class Statement {
	
	private String mnemonic;
	
	private Operand[] operands;
	
	
	private int position;
	
	private InstructionPattern matchedPattern;
	
	
	
	public Statement(String mnemonic, Operand[] operands) {
		if (mnemonic == null || operands == null)
			throw new NullPointerException();
		this.mnemonic = mnemonic;
		this.operands = operands;
	}
	
	
	
	public String getMnemonic() {
		return mnemonic;
	}
	
	
	
	public Operand[] getOperands() {
		return operands;
	}
	
	
	public int getPosition() {
		return position;
	}
	
	
	public InstructionPattern getMatchedPattern() {
		return matchedPattern;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(mnemonic);
		
		if (operands.length > 0) {
			sb.append("    ");
			
			boolean initial = true;
			for (Operand op : operands) {
				if (initial)
					initial = false;
				else
					sb.append(", ");
				sb.append(op);
			}
		}
		
		return sb.toString();
	}
	
}