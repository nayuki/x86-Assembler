package org.p79068.assembler.operand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * An operand for an instruction.
 * @see Immediate
 * @see Register
 * @see Memory32
 */
public class Operand {
	
	private static Pattern BASE_PATTERN = Pattern.compile("\\[ (%[a-z]+) \\]", Pattern.COMMENTS);
	
	private static Pattern BASE_INDEX_PATTERN = Pattern.compile("\\[ (%[a-z]+) \\+ (%[a-z]+) \\]", Pattern.COMMENTS);
	
	
	public static Operand parseOperand(String op) {
		Operand result = null;
		
		if (result == null) result = Register32.parseOperand(op);
		if (result == null) result = Register16.parseOperand(op);
		if (result == null) result = Register8.parseOperand(op);
		if (result == null) result = SegmentRegister.parseOperand(op);
		if (result == null) try { result = new Immediate(Integer.parseInt(op)); } catch (NumberFormatException e) {}
		
		if (result == null) {
			Matcher m = BASE_PATTERN.matcher(op);
			if (m.matches()) {
				Operand base = parseOperand(m.group(1));
				if (!(base instanceof Register32))
					throw new IllegalArgumentException("Invalid base register");
				result = new Memory32((Register32)base, 1, null, Immediate.ZERO);
			}
		}
		
		if (result == null) {
			Matcher m = BASE_INDEX_PATTERN.matcher(op);
			if (m.matches()) {
				Operand base = parseOperand(m.group(1));
				Operand index = parseOperand(m.group(2));
				if (!(base instanceof Register32))
					throw new IllegalArgumentException("Invalid base register");
				if (!(index instanceof Register32))
					throw new IllegalArgumentException("Invalid index register");
				result = new Memory32((Register32)base, 1, (Register32)index, Immediate.ZERO);
			}
		}
		
		
		if (result == null)
			throw new IllegalArgumentException("Illegal operand: " + op);
		
		return result;
	}
	
}