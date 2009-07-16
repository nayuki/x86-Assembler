package org.p79068.assembler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.p79068.assembler.operand.Operand;


/**
 * An instruction statement. It consists of a mnenomic and a list of operands. Immutable.
 */
public class InstructionStatement extends Statement {
	
	/**
	 * The instruction's mnemonic.
	 */
	private String mnemonic;
	
	/**
	 * The list of operands.
	 */
	private List<Operand> operands;
	
	
	
	/**
	 * Constructs an instruction statement with the specified mnemonic and list of operands.
	 * @param mnemonic the mnemonic
	 * @param operands the list of operands
	 * @throws NullPointerException if the mnemonic or list of operands or any operand is {@code null}
	 */
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
	
	
	
	/**
	 * Returns the mnemonic of this instruction statement.
	 * @return the mnemonic of this instruction statement
	 */
	public String getMnemonic() {
		return mnemonic;
	}
	
	
	/**
	 * Returns the list of operands of this instruction statement. The contents of the list cannot change or be changed.
	 * @return the list of operands of this instruction statement
	 */
	public List<Operand> getOperands() {
		return operands;
	}
	
	
	/**
	 * Compares this instruction statement to the specified object for equality. Returns {@code true} if the specified object is an instruction statement with the same mnemonic and operands. Otherwise returns {@code false}.
	 * @param obj the object to compare this instruction statement against
	 * @return {@code true} if the object is an instruction statement with the same mnemonic and operands, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabelStatement))
			return false;
		else {
			InstructionStatement ist = (InstructionStatement)obj;
			return mnemonic.equals(ist.mnemonic) && operands.equals(ist.operands);
		}
	}
	
	
	/**
	 * Returns the hash code for this instruction statement.
	 * @return the hash code for this instruction statement
	 */
	@Override
	public int hashCode() {
		return mnemonic.hashCode() + operands.hashCode();
	}
	
	
	/**
	 * Returns a string representation of this instruction statement. The format is subjected to change.
	 * @return a string representation of this instruction statement
	 */
	@Override
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
