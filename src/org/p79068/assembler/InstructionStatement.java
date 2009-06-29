package org.p79068.assembler;

import org.p79068.assembler.operand.Operand;


public class InstructionStatement extends Statement {
	
	private String mnemonic;
	
	private Operand[] operands;
	
	
	
	public InstructionStatement(String mnemonic, Operand[] operands) {
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
