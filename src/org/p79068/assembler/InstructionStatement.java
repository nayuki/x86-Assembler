package org.p79068.assembler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.p79068.assembler.operand.Operand;


public class InstructionStatement extends Statement {
	
	private String mnemonic;
	
	private List<Operand> operands;
	
	
	
	public InstructionStatement(String mnemonic, List<Operand> operands) {
		if (mnemonic == null || operands == null)
			throw new NullPointerException();
		for (Operand op : operands) {
			if (op == null)
				throw new NullPointerException();
		}
		
		this.mnemonic = mnemonic;
		this.operands = Collections.unmodifiableList(new ArrayList<Operand>(operands));
	}
	
	
	
	public String getMnemonic() {
		return mnemonic;
	}
	
	
	public List<Operand> getOperands() {
		return operands;
	}
	
	
	public boolean equals(Object obj) {
		if (!(obj instanceof LabelStatement))
			return false;
		else {
			InstructionStatement ist = (InstructionStatement)obj;
			return mnemonic.equals(ist.mnemonic) && operands.equals(ist.operands);
		}
	}
	
	
	public int hashCode() {
		return mnemonic.hashCode() + operands.hashCode();
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(mnemonic);
		
		if (operands.size() > 0) {
			sb.append("  ");
			
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
