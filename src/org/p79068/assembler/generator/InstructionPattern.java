package org.p79068.assembler.generator;

import java.util.regex.Pattern;

import static org.p79068.assembler.generator.OperandPattern.*;


public final class InstructionPattern {
	
	/**
	 * The regular expression pattern for mnemonics, which is one lowercase letter followed by zero or more lowercase or numeric characters.
	 */
	private static Pattern MNEMONIC_PATTERN = Pattern.compile("[a-z][a-z0-9]*");
	
	
	
	public final String mnemonic;
	
	public final OperandPattern[] operands;
	
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
		
		if (opcodes.length == 0)
			throw new IllegalArgumentException("Invalid opcodes");
		
		if (options.length > 1)
			throw new IllegalArgumentException("Invalid options");
		if (options.length == 1) {
			InstructionOption option = options[0];
			if (option instanceof RegisterInOpcode)
				checkOption((RegisterInOpcode)option, operands);
			else if (option instanceof ModRM)
				checkOption((ModRM)option, operands);
			else
				throw new AssertionError();
		}
		
		this.mnemonic = mnemonic;
		this.operands = operands.clone();
		this.operandSizeMode = operandSizeMode;
		this.options = options;
		this.opcodes = toBytes(opcodes);
	}
	
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(mnemonic);
		
		if (operands.length > 0) {
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
		if (option.parameterIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		if (!isRegisterOperandSlot(operands[option.parameterIndex]))
			throw new IllegalArgumentException("Option does not match operand");
	}
	
	
	private static void checkOption(ModRM option, OperandPattern[] operands) {
		if (option.rmParameterIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		if (!isRegisterMemoryOperandSlot(operands[option.rmParameterIndex]))
			throw new IllegalArgumentException("Option does not match operand");
		
		if (option.regOpcodeParameterIndex >= 10 && option.regOpcodeParameterIndex < 18);  // No problem
		else if (option.regOpcodeParameterIndex >= 18)
			throw new IllegalArgumentException("Invalid register/opcode constant value");
		else if (option.regOpcodeParameterIndex >= operands.length)
			throw new IndexOutOfBoundsException("Parameter index out of bounds");
		else if (!isRegisterOperandSlot(operands[option.regOpcodeParameterIndex]))
			throw new IllegalArgumentException("Option does not match operand");
	}
	
	
	private static boolean isRegisterMemoryOperandSlot(OperandPattern opslot) {
		return opslot == RM8
		    || opslot == RM16
		    || opslot == RM32
		    || opslot == MEM;
	}
	
	
	private static boolean isRegisterOperandSlot(OperandPattern opslot) {
		return opslot == REG8
		    || opslot == REG16
		    || opslot == REG32
		    || opslot == SREG;
	}
	
	
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
