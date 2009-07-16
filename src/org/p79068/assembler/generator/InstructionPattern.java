package org.p79068.assembler.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static org.p79068.assembler.generator.OperandPattern.*;


public final class InstructionPattern {
	
	/**
	 * The regular expression pattern for mnemonics, which is one lowercase letter followed by zero or more lowercase or numeric characters.
	 */
	private static Pattern MNEMONIC_PATTERN = Pattern.compile("[a-z][a-z0-9]*");
	
	
	
	public final String mnemonic;
	
	public final List<OperandPattern> operands;
	
	public final OperandSizeMode operandSizeMode;
	
	public final InstructionOption[] options;
	
	
	public final byte[] opcodes;
	
	
	
	public InstructionPattern(String mnemonic, OperandPattern[] operands, OperandSizeMode operandSizeMode, int[] opcodes, InstructionOption... options) {
		if (mnemonic == null || operands == null || operandSizeMode == null || opcodes == null || options == null)
			throw new NullPointerException();
		
		if (!MNEMONIC_PATTERN.matcher(mnemonic).matches())
			throw new IllegalArgumentException("Invalid mnemonic");
		
		if (operands.length > 10)
			throw new IllegalArgumentException("Invalid operands");
		
		if (options.length > 1)
			throw new IllegalArgumentException("Invalid options");
		if (options.length == 1) {
			InstructionOption option = options[0];
			if (option instanceof RegisterInOpcode)
				checkOption((RegisterInOpcode)option, operands);
			else if (option instanceof ModRM)
				checkOption((ModRM)option, operands);
			else
				throw new AssertionError("Unrecognized instruction option");
		}
		
		this.mnemonic = mnemonic;
		this.operandSizeMode = operandSizeMode;
		this.options = options;
		this.opcodes = toBytes(opcodes);
		
		List<OperandPattern> temp = new ArrayList<OperandPattern>();
		Collections.addAll(temp, operands);
		this.operands = Collections.unmodifiableList(temp);
	}
	
	
	
	/**
	 * Returns a string representation of this instruction pattern. The format is subjected to change.
	 * @return a string representation of this instruction pattern
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(mnemonic);
		
		if (operands.size() > 0) {
			sb.append("  ");
			boolean initial = true;
			for (OperandPattern op : operands) {
				if (initial)
					initial = false;
				else
					sb.append(", ");
				sb.append(op);
			}
		}
		
		return sb.toString();
	}
	
	
	
	private static void checkOption(RegisterInOpcode option, OperandPattern[] operands) {
		if (option.operandIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		if (!isRegisterPattern(operands[option.operandIndex]))
			throw new IllegalArgumentException("Option does not match operand");
	}
	
	
	private static void checkOption(ModRM option, OperandPattern[] operands) {
		if (option.rmOperandIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		if (!isRegisterMemoryPattern(operands[option.rmOperandIndex]))
			throw new IllegalArgumentException("Option does not match operand");
		
		if (option.regOpcodeOperandIndex >= 10 && option.regOpcodeOperandIndex < 18);  // No problem
		else if (option.regOpcodeOperandIndex >= 18)
			throw new IllegalArgumentException("Invalid register/opcode constant value");
		else if (option.regOpcodeOperandIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		else if (!isRegisterPattern(operands[option.regOpcodeOperandIndex]))
			throw new IllegalArgumentException("Option does not match operand");
	}
	
	
	private static boolean isRegisterMemoryPattern(OperandPattern pat) {
		return pat == RM8
		    || pat == RM16
		    || pat == RM32
		    || pat == MEM;
	}
	
	
	private static boolean isRegisterPattern(OperandPattern pat) {
		return pat == REG8
		    || pat == REG16
		    || pat == REG32
		    || pat == SREG;
	}
	
	
	/**
	 * Returns a new unsigned byte array containing the same sequence of values as the specified int32 array. Each integer value must be in the range [0x00, 0xFF].
	 * @param opcodes
	 * @return a new byte array containing the same sequence of values as the int32 array
	 * @throws IllegalArgumentException if any value of the int32 array is outside of the range [0x00, 0xFF]
	 */
	private static byte[] toBytes(int[] opcodes) {
		byte[] result = new byte[opcodes.length];
		for (int i = 0; i < opcodes.length; i++) {
			if ((opcodes[i] & 0xFF) != opcodes[i])
				throw new IllegalArgumentException("Byte value out of range");
			result[i] = (byte)opcodes[i];
		}
		return result;
	}
	
}
