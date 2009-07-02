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
import org.p79068.assembler.operand.Memory;
import org.p79068.assembler.operand.Operand;
import org.p79068.assembler.operand.Register;
import org.p79068.assembler.operand.Register16;
import org.p79068.assembler.operand.Register32;
import org.p79068.assembler.operand.Register8;
import org.p79068.assembler.operand.SegmentRegister;


public final class Parser {
	
	public static Program parseFile(File inputFile) throws IOException {
		if (inputFile == null)
			throw new NullPointerException();
		BufferedTokenizer tokenizer = new BufferedTokenizer(new Tokenizer(inputFile));
		return new Parser(tokenizer).parseFile();
	}
	
	
	
	private BufferedTokenizer tokenizer;
	
	
	
	private Parser(BufferedTokenizer tokenizer) {
		if (tokenizer == null)
			throw new NullPointerException();
		this.tokenizer = tokenizer;
	}
	
	
	
	private Program parseFile() {
		Program program = new Program();
		while (!tokenizer.check(TokenType.END_OF_FILE))
			parseLine(program);
		return program;
	}
	
	
	private void parseLine(Program program) {
		// Parse label declarations
		while (tokenizer.check(TokenType.LABEL)) {
			String name = tokenizer.next().text;
			name = name.substring(0, name.length() - 1);
			program.addStatement(new LabelStatement(name));
		}
		
		// Parse instruction
		if (tokenizer.check(TokenType.NAME))
			parseInstruction(program);
		
		if (tokenizer.check(TokenType.NEWLINE))
			tokenizer.next();
		else
			throw new RuntimeException("Expected newline");
	}
	
	
	private void parseInstruction(Program program) {
		// Parse mnemonic (easy)
		String mnemonic = tokenizer.next().text;
		
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
				tokenizer.next();
			}
			
			if (tokenizer.check(TokenType.REGISTER)) {
				operands.add(parseRegister(tokenizer.next().text));
			} else if (tokenizer.check(TokenType.DOLLAR)) {
				tokenizer.next();
				operands.add(parseImmediate());
			} else if (canParseImmediate() || tokenizer.check(TokenType.LEFT_PAREN)) {
				Immediate disp;
				if (canParseImmediate())
					disp = parseImmediate();
				else
					disp = ImmediateValue.ZERO;
				operands.add(parseMemory(disp));
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
	private boolean canParseImmediate() {
		return tokenizer.check(TokenType.DECIMAL)
		    || tokenizer.check(TokenType.HEXADECIMAL)
		    || tokenizer.check(TokenType.NAME);
	}
	
	
	private Immediate parseImmediate() {
		if (tokenizer.check(TokenType.DECIMAL)) {
			return new ImmediateValue(Integer.parseInt(tokenizer.next().text));
		} else if (tokenizer.check(TokenType.HEXADECIMAL)) {
			String text = tokenizer.next().text;
			text = text.substring(2, text.length());
			return new ImmediateValue((int)Long.parseLong(text, 16));
		} else if (tokenizer.check(TokenType.NAME)) {
			return new Label(tokenizer.next().text);
		} else {
			throw new RuntimeException("Expected immediate");
		}
	}
	
	
	private Memory parseMemory(Immediate displacement) {
		Register32 base = null;
		Register32 index = null;
		int scale = 1;
		
		if (tokenizer.check(TokenType.LEFT_PAREN)) {
			tokenizer.next();
			
			if (tokenizer.check(TokenType.REGISTER))
				base = (Register32)parseRegister(tokenizer.next().text);
			
			if (tokenizer.check(TokenType.COMMA)) {
				tokenizer.next();
				
				if (tokenizer.check(TokenType.REGISTER))
					index = (Register32)parseRegister(tokenizer.next().text);
				
				if (tokenizer.check(TokenType.COMMA)) {
					tokenizer.next();
					
					if (tokenizer.check(TokenType.DECIMAL))
						scale = Integer.parseInt(tokenizer.next().text);
				}
				
			}
			
			if (tokenizer.check(TokenType.RIGHT_PAREN))
				tokenizer.next();
			else
				throw new RuntimeException("Expected right parenthesis");
		}
		
		return new Memory(base, index, scale, displacement);
	}
	
	
	private static Map<String, Register> REGISTER_TABLE;
	
	static {
		REGISTER_TABLE = new HashMap<String, Register>();
		REGISTER_TABLE.put("%eax", Register32.EAX);
		REGISTER_TABLE.put("%ebx", Register32.EBX);
		REGISTER_TABLE.put("%ecx", Register32.ECX);
		REGISTER_TABLE.put("%edx", Register32.EDX);
		REGISTER_TABLE.put("%esp", Register32.ESP);
		REGISTER_TABLE.put("%ebp", Register32.EBP);
		REGISTER_TABLE.put("%esi", Register32.ESI);
		REGISTER_TABLE.put("%edi", Register32.EDI);
		REGISTER_TABLE.put("%ax", Register16.AX);
		REGISTER_TABLE.put("%bx", Register16.BX);
		REGISTER_TABLE.put("%cx", Register16.CX);
		REGISTER_TABLE.put("%dx", Register16.DX);
		REGISTER_TABLE.put("%sp", Register16.SP);
		REGISTER_TABLE.put("%bp", Register16.BP);
		REGISTER_TABLE.put("%si", Register16.SI);
		REGISTER_TABLE.put("%di", Register16.DI);
		REGISTER_TABLE.put("%al", Register8.AL);
		REGISTER_TABLE.put("%bl", Register8.BL);
		REGISTER_TABLE.put("%cl", Register8.CL);
		REGISTER_TABLE.put("%dl", Register8.DL);
		REGISTER_TABLE.put("%ah", Register8.AH);
		REGISTER_TABLE.put("%bh", Register8.BH);
		REGISTER_TABLE.put("%ch", Register8.CH);
		REGISTER_TABLE.put("%dh", Register8.DH);
		REGISTER_TABLE.put("%cs", SegmentRegister.CS);
		REGISTER_TABLE.put("%ds", SegmentRegister.DS);
		REGISTER_TABLE.put("%es", SegmentRegister.ES);
		REGISTER_TABLE.put("%fs", SegmentRegister.FS);
		REGISTER_TABLE.put("%gs", SegmentRegister.GS);
		REGISTER_TABLE.put("%ss", SegmentRegister.SS);
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
	
}
