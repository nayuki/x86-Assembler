package org.p79068.assembler.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.p79068.assembler.InstructionStatement;
import org.p79068.assembler.LabelStatement;
import org.p79068.assembler.Program;
import org.p79068.assembler.operand.Immediate;
import org.p79068.assembler.operand.ImmediateValue;
import org.p79068.assembler.operand.Label;
import org.p79068.assembler.operand.Memory32;
import org.p79068.assembler.operand.Operand;
import org.p79068.assembler.operand.Register;
import org.p79068.assembler.operand.Register16;
import org.p79068.assembler.operand.Register32;
import org.p79068.assembler.operand.Register8;
import org.p79068.assembler.operand.SegmentRegister;


public final class Parser {
	
	public static Program parseFile(File inputFile) throws IOException {
		BufferedTokenizer tokenizer = new BufferedTokenizer(new Tokenizer(inputFile));
		Program program = new Program();
		
		while (!tokenizer.check(TokenType.END_OF_FILE))
			parseLine(tokenizer, program);
		
		return program;
	}
	
	
	private static void parseLine(BufferedTokenizer tokenizer, Program program) {
		// Parse label declarations
		while (tokenizer.check(TokenType.LABEL)) {
			String name = tokenizer.nextToken().text;
			name = name.substring(0, name.length() - 1);
			program.addStatement(new LabelStatement(name));
		}
		
		// Parse instruction
		if (tokenizer.check(TokenType.NAME))
			parseInstruction(tokenizer, program);
		
		if (tokenizer.check(TokenType.NEWLINE))
			tokenizer.nextToken();
		else
			throw new RuntimeException("Expected newline");
	}
	
	
	private static void parseInstruction(BufferedTokenizer tokenizer, Program program) {
		// Parse mnemonic (easy)
		String mnemonic = tokenizer.nextToken().text;
		
		// Parse operands (hard)
		List<Operand> operands = new ArrayList<Operand>();
		boolean expectcomma = false;
		while (!tokenizer.check(TokenType.NEWLINE)) {
			if (!expectcomma) {
				if (tokenizer.check(TokenType.COMMA))
					throw new RuntimeException("Expected operand, got comma");
			} else {
				if (!tokenizer.check(TokenType.COMMA))
					throw new RuntimeException("Expected comma");
				tokenizer.nextToken();
			}
			
			if (tokenizer.check(TokenType.REGISTER)) {
				operands.add(parseRegister(tokenizer.nextToken().text));
			} else if (tokenizer.check(TokenType.DOLLAR)) {
				tokenizer.nextToken();
				operands.add(parseImmediate(tokenizer));
			} else if (canParseImmediate(tokenizer) || tokenizer.check(TokenType.LEFT_PAREN)) {
				Immediate disp;
				if (canParseImmediate(tokenizer))
					disp = parseImmediate(tokenizer);
				else
					disp = ImmediateValue.ZERO;
				operands.add(parseMemory(tokenizer, disp));
			} else {
				throw new RuntimeException("Expected operand");
			}
			expectcomma = true;
		}
		
		program.addStatement(new InstructionStatement(mnemonic, operands.toArray(new Operand[operands.size()])));
	}
	
	
	/**
	 * Tests whether the next token is a decimal number, hexadecimal number, or a label.
	 * @param tokenizer the tokenizer to test from
	 * @return {@code true} if the next token is an immediate operand, {@code false} otherwise
	 */
	private static boolean canParseImmediate(BufferedTokenizer tokenizer) {
		return tokenizer.check(TokenType.DECIMAL)
		    || tokenizer.check(TokenType.HEXADECIMAL)
		    || tokenizer.check(TokenType.NAME);
	}
	
	
	private static Immediate parseImmediate(BufferedTokenizer tokenizer) {
		if (tokenizer.check(TokenType.DECIMAL)) {
			return new ImmediateValue(Integer.parseInt(tokenizer.nextToken().text));
		} else if (tokenizer.check(TokenType.HEXADECIMAL)) {
			String text = tokenizer.nextToken().text;
			text = text.substring(2, text.length());
			return new ImmediateValue((int)Long.parseLong(text, 16));
		} else if (tokenizer.check(TokenType.NAME)) {
			return new Label(tokenizer.nextToken().text);
		} else {
			throw new RuntimeException("Expected immediate");
		}
	}
	
	
	private static Memory32 parseMemory(BufferedTokenizer tokenizer, Immediate displacement) {
		Register32 base = null;
		Register32 index = null;
		int scale = 1;
		
		if (tokenizer.check(TokenType.LEFT_PAREN)) {
			tokenizer.nextToken();
			
			if (tokenizer.check(TokenType.REGISTER))
				base = (Register32)parseRegister(tokenizer.nextToken().text);
			
			if (tokenizer.check(TokenType.COMMA)) {
				tokenizer.nextToken();
				
				if (tokenizer.check(TokenType.REGISTER))
					index = (Register32)parseRegister(tokenizer.nextToken().text);
				
				if (tokenizer.check(TokenType.COMMA)) {
					tokenizer.nextToken();
					
					if (tokenizer.check(TokenType.DECIMAL))
						scale = Integer.parseInt(tokenizer.nextToken().text);
				}
				
			}
			
			if (tokenizer.check(TokenType.RIGHT_PAREN))
				tokenizer.nextToken();
			else
				throw new RuntimeException("Expected right parenthesis");
		}
		
		return new Memory32(base, index, scale, displacement);
	}
	
	
	private static Map<String, Register> REGISTER_TABLE;
	
	static {
		REGISTER_TABLE = new HashMap<String, Register>();
		REGISTER_TABLE.put("%eax", Register32.EAX_REGISTER);
		REGISTER_TABLE.put("%ebx", Register32.EBX_REGISTER);
		REGISTER_TABLE.put("%ecx", Register32.ECX_REGISTER);
		REGISTER_TABLE.put("%edx", Register32.EDX_REGISTER);
		REGISTER_TABLE.put("%esp", Register32.ESP_REGISTER);
		REGISTER_TABLE.put("%ebp", Register32.EBP_REGISTER);
		REGISTER_TABLE.put("%esi", Register32.ESI_REGISTER);
		REGISTER_TABLE.put("%edi", Register32.EDI_REGISTER);
		REGISTER_TABLE.put("%ax", Register16.AX_REGISTER);
		REGISTER_TABLE.put("%bx", Register16.BX_REGISTER);
		REGISTER_TABLE.put("%cx", Register16.CX_REGISTER);
		REGISTER_TABLE.put("%dx", Register16.DX_REGISTER);
		REGISTER_TABLE.put("%sp", Register16.SP_REGISTER);
		REGISTER_TABLE.put("%bp", Register16.BP_REGISTER);
		REGISTER_TABLE.put("%si", Register16.SI_REGISTER);
		REGISTER_TABLE.put("%di", Register16.DI_REGISTER);
		REGISTER_TABLE.put("%al", Register8.AL_REGISTER);
		REGISTER_TABLE.put("%bl", Register8.BL_REGISTER);
		REGISTER_TABLE.put("%cl", Register8.CL_REGISTER);
		REGISTER_TABLE.put("%dl", Register8.DL_REGISTER);
		REGISTER_TABLE.put("%ah", Register8.AH_REGISTER);
		REGISTER_TABLE.put("%bh", Register8.BH_REGISTER);
		REGISTER_TABLE.put("%ch", Register8.CH_REGISTER);
		REGISTER_TABLE.put("%dh", Register8.DH_REGISTER);
		REGISTER_TABLE.put("%cs", SegmentRegister.CS_REGISTER);
		REGISTER_TABLE.put("%ds", SegmentRegister.DS_REGISTER);
		REGISTER_TABLE.put("%es", SegmentRegister.ES_REGISTER);
		REGISTER_TABLE.put("%fs", SegmentRegister.FS_REGISTER);
		REGISTER_TABLE.put("%gs", SegmentRegister.GS_REGISTER);
		REGISTER_TABLE.put("%ss", SegmentRegister.SS_REGISTER);
	}
	
	
	/**
	 * Returns the register associated with the specified name. Examples of register names include {@code "%eax"}, {@code "%sp"}, and {@code "%cs"}. The name is case-insensitive.
	 * @param name the name of the register
	 * @return the register associated with the name
	 * @throws IllegalArgumentException if no register is associated with the name
	 */
	private static Register parseRegister(String name) {
		name = name.toLowerCase();
		if (!REGISTER_TABLE.containsKey(name))
			throw new IllegalArgumentException("Invalid register name");
		return REGISTER_TABLE.get(name);
	}
	
	
	
	/**
	 * Not instantiable.
	 */
	private Parser() {}
	
}
