package org.p79068.assembler.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.p79068.assembler.InstructionStatement;
import org.p79068.assembler.LabelStatement;
import org.p79068.assembler.Program;
import org.p79068.assembler.operand.Label;
import org.p79068.assembler.operand.Operand;


public final class Parser {
	
	public static Program parseFile(File inputFile) throws IOException {
		Program program = new Program();
		
		BufferedTokenizer tokenizer = new BufferedTokenizer(new Tokenizer(inputFile));
		
		while (true) {
			if (tokenizer.check(TokenType.END_OF_FILE))
				break;
			
			while (tokenizer.check(TokenType.LABEL)) {
				String name = tokenizer.nextToken().getText();
				program.addStatement(new LabelStatement(name));
			}
			
			if (tokenizer.check(TokenType.NAME)) {
				String mnemonic = tokenizer.nextToken().getText();
				
				List<Operand> operands = new ArrayList<Operand>();
				while (!tokenizer.check(TokenType.NEWLINE)) {
					if (tokenizer.check(TokenType.COMMA))
						tokenizer.nextToken();
					if (tokenizer.check(TokenType.REGISTER))
						operands.add(Operand.parseOperand(tokenizer.nextToken().getText()));
					else if (tokenizer.check(TokenType.DECIMAL))
						operands.add(Operand.parseOperand(tokenizer.nextToken().getText()));
					else if (tokenizer.check(TokenType.NAME))
						operands.add(new Label(tokenizer.nextToken().getText()));
					else
						throw new RuntimeException();
				}
				
				program.addStatement(new InstructionStatement(mnemonic, operands.toArray(new Operand[operands.size()])));
			}
			
			if (tokenizer.check(TokenType.NEWLINE))
				tokenizer.nextToken();
			else
				throw new RuntimeException();
		}
		
		return program;
	}
	
	
	
	/**
	 * Not instantiable.
	 */
	private Parser() {}
	
}
