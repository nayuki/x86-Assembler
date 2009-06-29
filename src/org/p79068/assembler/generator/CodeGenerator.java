package org.p79068.assembler.generator;

import java.util.Set;

import org.p79068.assembler.operand.Immediate;
import org.p79068.assembler.operand.Memory32;
import org.p79068.assembler.operand.Operand;
import org.p79068.assembler.operand.OperandSizeMode;
import org.p79068.assembler.operand.OperandSlot;
import org.p79068.assembler.operand.Register;
import org.p79068.assembler.operand.Register32;


public final class CodeGenerator {
	
	public static byte[] getMachineCode(Set<InstructionPattern> patterns, String mnemonic, Operand[] operands) {
		// Get matching instruction pattern
		InstructionPattern patt = match(patterns, mnemonic, operands);
		
		// Initialize blank result
		byte[] result = new byte[0];
		
		// Append operand size override prefix if necessary
		if (patt.operandSizeMode == OperandSizeMode.MODE16)
			result = concatenate(result, new byte[]{0x66});
		
		// Process register-in-opcode option
		byte[] opcodes = patt.opcodes;
		if (patt.options.length == 1 && patt.options[0] instanceof RegisterInOpcode) {
			RegisterInOpcode option = (RegisterInOpcode)patt.options[0];
			opcodes = opcodes.clone();
			opcodes[opcodes.length - 1] += ((Register)operands[option.parameterIndex]).getRegisterNumber();
		}
		
		// Append opcode
		result = concatenate(result, opcodes);
		
		// Append ModR/M and SIB bytes if necessary
		if (patt.options.length == 1 && patt.options[0] instanceof ModRM)
			result = concatenate(result, makeModRMBytes((ModRM)patt.options[0], operands));
		
		// Append immediate operands if necessary
		for (int i = 0; i < patt.operands.length; i++) {
			OperandSlot slot = patt.operands[i];
			if (slot == OperandSlot.IMM8) {
				int value = ((Immediate)operands[i]).getValue();
				result = concatenate(result, new byte[]{(byte)(value >>> 0)});
			} else if (slot == OperandSlot.IMM16) {
				int value = ((Immediate)operands[i]).getValue();
				result = concatenate(result, new byte[]{(byte)(value >>> 0), (byte)(value >>> 8)});
			} else if (slot == OperandSlot.IMM32) {
				int value = ((Immediate)operands[i]).getValue();
				result = concatenate(result, new byte[]{(byte)(value >>> 0), (byte)(value >>> 8), (byte)(value >>> 16), (byte)(value >>> 24)});
			}
		}
		
		// Return machine code sequence
		return result;
	}
	
	
	private static InstructionPattern match(Set<InstructionPattern> patterns, String mnemonic, Operand[] operands) {
		for (InstructionPattern patt : patterns) {
			if (matches(patt, mnemonic, operands)) {
				return patt;
			}
		}
		throw new IllegalArgumentException("No match: " + mnemonic);
	}
	
	
	private static boolean matches(InstructionPattern patt, String mnemonic, Operand[] operands) {
		if (!patt.mnemonic.equals(mnemonic))
			return false;
		if (patt.operands.length != operands.length)
			return false;
		for (int i = 0; i < patt.operands.length && i < operands.length; i++) {
			if (!patt.operands[i].matches(operands[i]))
				return false;
		}
		return true;
	}
	
	
	private static byte[] makeModRMBytes(ModRM option, Operand[] operands) {
		Operand rm = operands[option.rmParameterIndex];
		int mod;
		int rmvalue;
		byte[] rest;
		
		if (rm instanceof Register) {
			mod = 3;
			rmvalue = ((Register)rm).getRegisterNumber();
			rest = new byte[0];
			
		} else if (rm instanceof Memory32) {
			Memory32 m = (Memory32)rm;
			
			if (m.getBase() == null && m.getIndex() == null) {  // disp32
				mod = 0;
				rmvalue = 5;
				rest = m.getDisplacement().to4Bytes();
				
			} else if (m.getBase() != Register32.ESP_REGISTER && m.getBase() != Register32.EBP_REGISTER && m.getIndex() == null && m.getDisplacement().isZero()) {  // eax, ecx, edx, ebx, esi, edi
				mod = 0;
				rmvalue = m.getBase().getRegisterNumber();
				rest = new byte[0];
				
			} else if (m.getBase() != Register32.ESP_REGISTER && m.getIndex() == null && m.getDisplacement().isSigned8Bit()) {  // (eax, ecx, edx, ebx, ebp, esi, edi) + disp8
				mod = 1;
				rmvalue = m.getBase().getRegisterNumber();
				rest = m.getDisplacement().to1Byte();
				
			} else if (m.getBase() != Register32.ESP_REGISTER && m.getIndex() == null) {  // (eax, ecx, edx, ebx, ebp, esi, edi) + disp32
				mod = 2;
				rmvalue = m.getBase().getRegisterNumber();
				rest = m.getDisplacement().to4Bytes();
				
			} else {  // SIB
				rmvalue = 4;
				
				if (m.getBase() == null) {  // index*scale + disp32
					mod = 0;
					rest = m.getDisplacement().to4Bytes();
					
				} else if (m.getBase() != Register32.EBP_REGISTER && m.getDisplacement().isZero()) {  // (eax, ecx, edx, ebx, esp, esi, edi) + index*scale
					mod = 0;
					rest = new byte[0];
					
				} else if (m.getDisplacement().isSigned8Bit()) {  // base + index*scale + disp8
					mod = 1;
					rest = m.getDisplacement().to1Byte();
					
				} else {  // base + index*scale + disp32
					mod = 2;
					rest = m.getDisplacement().to4Bytes();
				}
				
				int scale = getScaleNumber(m.getScale());
				int index = getIndexNumber(m.getIndex());
				int base = getBaseNumber(m.getBase());
				
				byte[] sib = {(byte)(scale << 6 | index << 3 | base << 0)};
				rest = concatenate(sib, rest);
			}
			
		} else
			throw new AssertionError();
		
		// Set reg/op value
		int regopvalue;
		if (option.regOpcodeParameterIndex < 10) {
			Register regop = (Register)operands[option.regOpcodeParameterIndex];
			regopvalue = regop.getRegisterNumber();
		} else
			regopvalue = option.regOpcodeParameterIndex - 10;
		
		// Make ModR/M byte
		byte[] modrm = {(byte)(mod << 6 | regopvalue << 3 | rmvalue << 0)};
		
		// Concatenate and return
		return concatenate(modrm, rest);
	}
	
	
	private static int getBaseNumber(Register32 base) {
		if (base != null)
			return base.getRegisterNumber();
		else
			return 5;
	}
	
	
	private static int getIndexNumber(Register32 index) {
		if (index == Register32.ESP_REGISTER)
			throw new IllegalArgumentException();
		if (index != null)
			return index.getRegisterNumber();
		else
			return 4;
	}
	
	
	private static int getScaleNumber(int scale) {
		switch (scale) {
			case 1:  return 0;
			case 2:  return 1;
			case 4:  return 2;
			case 8:  return 3;
			default:  throw new AssertionError();
		}
	}
	
	
	private static byte[] concatenate(byte[]... arrays) {
		int totalLength = 0;
		for (byte[] b : arrays)
			totalLength += b.length;
		
		byte[] result = new byte[totalLength];
		int offset = 0;
		for (byte[] b : arrays) {
			System.arraycopy(b, 0, result, offset, b.length);
			offset += b.length;
		}
		return result;
	}
	
	
	
	private CodeGenerator() {}
	
}