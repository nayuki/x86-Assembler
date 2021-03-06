package org.p79068.assembler.parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class FastStringTokenizer extends Tokenizer {
	
	private static String read(File file) throws IOException {
		if (file == null)
			throw new NullPointerException();
		
		Reader in = new InputStreamReader(new BufferedInputStream(new FileInputStream(file)));
		
		StringBuilder sb = new StringBuilder();
		char[] buffer = new char[32 * 1024];
		while (true) {
			int read = in.read(buffer);
			if (read == -1)
				break;
			sb.append(buffer, 0, read);
		}
		
		sb.append('\n');
		
		return sb.toString();
	}
	
	
	
	private String sourceCode;
	
	private int offset;
	
	
	
	public FastStringTokenizer(File file) throws IOException {
		if (file == null)
			throw new NullPointerException();
		sourceCode = read(file);
		offset = 0;
	}
	
	
	FastStringTokenizer(String sourceCode) {
		if (sourceCode == null)
			throw new NullPointerException();
		this.sourceCode = sourceCode;
		offset = 0;
	}
	
	
	
	/**
	 * Returns the next token from this tokenizer.
	 * @return the next token from this tokenizer
	 */
	@Override
	public Token next() {
		int start = offset;
		
		switch (peekChar()) {
			case -1:
				return new Token(TokenType.END_OF_FILE, "");
			
			case '$':
				offset++;
				return new Token(TokenType.DOLLAR, "$");
			
			case '(':
				offset++;
				return new Token(TokenType.LEFT_PAREN, "(");
			
			case ')':
				offset++;
				return new Token(TokenType.RIGHT_PAREN, ")");
			
			case ',':
				offset++;
				return new Token(TokenType.COMMA, ",");
			
			case '+':
				offset++;
				return new Token(TokenType.PLUS, "+");
			
			case '-':
				offset++;
				if (isDecimal(peekChar())) {
					offset++;
					while (isDecimal(peekChar()))
						offset++;
					return new Token(TokenType.DECIMAL, sourceCode.substring(start, offset));
				} else {
					return new Token(TokenType.MINUS, "-");
				}
			
			case '\n':
			case '\r':
				offset++;
				while (peekChar() == '\r' || peekChar() == '\n')
					offset++;
				return new Token(TokenType.NEWLINE, sourceCode.substring(start, offset));
			
			case ' ':  // Whitespace
			case '\t':
				offset++;
				while (peekChar() == ' ' || peekChar() == '\t')
					offset++;
				return next();
			
			case '#':  // Comment
				offset++;
				while (peekChar() != '\r' && peekChar() != '\n')
					offset++;
				return next();
			
			case '%':
				offset++;
				if (!isNameStart(peekChar()))
					throw new RuntimeException("No token pattern match");
				offset++;
				while (isNameContinuation(peekChar()))
					offset++;
				return new Token(TokenType.REGISTER, sourceCode.substring(start, offset));
			
			case '0':
				offset++;
				if (peekChar() == -1)
					return new Token(TokenType.DECIMAL, "0");
				if (peekChar() != 'x' && peekChar() != 'X') {
					while (isDecimal(peekChar()))
						offset++;
					return new Token(TokenType.DECIMAL, sourceCode.substring(start, offset));
				} else {
					offset++;
					while (isHexadecimal(peekChar()))
						offset++;
					return new Token(TokenType.HEXADECIMAL, sourceCode.substring(start, offset));
				}
			
			default:
				if (isNameStart(peekChar())) {
					offset++;
					while (isNameContinuation(peekChar()))
						offset++;
					
					if (peekChar() == ':') {
						offset++;
						return new Token(TokenType.LABEL, sourceCode.substring(start, offset));
					} else {
						return new Token(TokenType.NAME, sourceCode.substring(start, offset));
					}
					
				} else if (isDecimal(peekChar())) {
					offset++;
					while (isDecimal(peekChar()))
						offset++;
					return new Token(TokenType.DECIMAL, sourceCode.substring(start, offset));
					
				} else {
					throw new RuntimeException("No token pattern match");
				}
		}
	}
	
	
	private int peekChar() {
		if (offset == sourceCode.length())
			return -1;
		else
			return sourceCode.charAt(offset);
	}
	
	
	private static boolean isNameStart(int c) {
		return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '_';
	}
	
	
	private static boolean isNameContinuation(int c) {
		return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '_';
	}
	
	
	private static boolean isDecimal(int c) {
		return c >= '0' && c <= '9';
	}
	
	
	private static boolean isHexadecimal(int c) {
		return c >= 'A' && c <= 'F' || c >= 'a' && c <= 'f' || c >= '0' && c <= '9';
	}
	
}
