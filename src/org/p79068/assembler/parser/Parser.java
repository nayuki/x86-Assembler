package org.p79068.assembler.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.p79068.assembler.InstructionStatement;
import org.p79068.assembler.LabelStatement;
import org.p79068.assembler.Program;
import org.p79068.assembler.operand.Immediate;
import org.p79068.assembler.operand.ImmediateValue;
import org.p79068.assembler.operand.Label;
import org.p79068.assembler.operand.Memory32;
import org.p79068.assembler.operand.Operand;
import org.p79068.assembler.operand.Register;
import org.p79068.assembler.operand.Register32;


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
				operands.add(Register.parseRegister(tokenizer.nextToken().text));
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
				base = Register32.parseOperand(tokenizer.nextToken().text);
			
			if (tokenizer.check(TokenType.COMMA)) {
				tokenizer.nextToken();
				
				if (tokenizer.check(TokenType.REGISTER))
					index = Register32.parseOperand(tokenizer.nextToken().text);
				
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
	
	
	
	/**
	 * Not instantiable.
	 */
	private Parser() {}
	
}
