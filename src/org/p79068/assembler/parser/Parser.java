package org.p79068.assembler.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.p79068.assembler.InstructionStatement;
import org.p79068.assembler.LabelStatement;
import org.p79068.assembler.Program;
import org.p79068.assembler.operand.Immediate;
import org.p79068.assembler.operand.Label;
import org.p79068.assembler.operand.Memory32;
import org.p79068.assembler.operand.Operand;
import org.p79068.assembler.operand.Register32;


public final class Parser {
	
	public static Program parseFile(File inputFile) throws IOException {
		Program program = new Program();
		
		BufferedTokenizer tokenizer = new BufferedTokenizer(new Tokenizer(inputFile));
		
		while (true) {
			if (tokenizer.check(TokenType.END_OF_FILE))
				break;
			
			while (tokenizer.check(TokenType.LABEL)) {
				String name = tokenizer.nextToken().text;
				program.addStatement(new LabelStatement(name));
			}
			
			if (tokenizer.check(TokenType.NAME)) {
				String mnemonic = tokenizer.nextToken().text;
				
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
					
					if (tokenizer.check(TokenType.REGISTER))
						operands.add(Operand.parseOperand(tokenizer.nextToken().text));
					else if (tokenizer.check(TokenType.DECIMAL)) {
						Immediate op = (Immediate)Operand.parseOperand(tokenizer.nextToken().text);
						Memory32 m = parseMemory(tokenizer, op);
						if (m == null)
							operands.add(op);
						else
							operands.add(m);
					} else if (tokenizer.check(TokenType.NAME)) {
						Immediate op = (Immediate)(Object)new Label(tokenizer.nextToken().text);
						Memory32 m = parseMemory(tokenizer, op);
						if (m == null)
							operands.add(op);
						else
							operands.add(m);
					} else if (tokenizer.check(TokenType.LEFT_PAREN))
						operands.add(parseMemory(tokenizer, Immediate.ZERO));
					else
						throw new RuntimeException("Expected operand");
					expectcomma = true;
				}
				
				program.addStatement(new InstructionStatement(mnemonic, operands.toArray(new Operand[operands.size()])));
			}
			
			if (tokenizer.check(TokenType.NEWLINE))
				tokenizer.nextToken();
			else
				throw new RuntimeException("Expected newline");
		}
		
		return program;
	}
	
	
	
	private static Memory32 parseMemory(BufferedTokenizer tokenizer, Immediate displacement) {
		if (tokenizer.check(TokenType.LEFT_PAREN))
			tokenizer.nextToken();
		else
			return null;
		
		Register32 base = null;
		Register32 index = null;
		int scale = 1;
		
		if (tokenizer.check(TokenType.REGISTER))
			base = (Register32)Operand.parseOperand(tokenizer.nextToken().text);
		
		if (tokenizer.check(TokenType.COMMA)) {
			tokenizer.nextToken();
			
			if (tokenizer.check(TokenType.REGISTER))
				index = (Register32)Operand.parseOperand(tokenizer.nextToken().text);
			
			if (tokenizer.check(TokenType.COMMA)) {
				tokenizer.nextToken();
				
				if (tokenizer.check(TokenType.DECIMAL))
					scale = Integer.parseInt(tokenizer.nextToken().text);
			}
			
		}
		
		if (tokenizer.check(TokenType.RIGHT_PAREN)) {
			tokenizer.nextToken();
		} else
			throw new RuntimeException();
		
		return new Memory32(base, scale, index, displacement);
	}
	
	
	
	/**
	 * Not instantiable.
	 */
	private Parser() {}
	
}
