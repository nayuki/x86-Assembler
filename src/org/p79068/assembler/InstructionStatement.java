package org.p79068.assembler;

import java.util.Arrays;

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
	
	
	public boolean equals(Object obj) {
		if (!(obj instanceof LabelStatement))
			return false;
		else {
			InstructionStatement ist = (InstructionStatement)obj;
			return mnemonic.equals(ist.mnemonic) && Arrays.deepEquals(operands, ist.operands);
		}
	}
	
	
	public int hashCode() {
		return mnemonic.hashCode() + Arrays.deepHashCode(operands);
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
