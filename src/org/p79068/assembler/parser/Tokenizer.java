package org.p79068.assembler.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


final class Tokenizer {
	
	private String sourceCode;
	
	private int offset;
	
	
	
	public Tokenizer(File file) throws IOException {
		sourceCode = FileReader.read(file);
		offset = 0;
	}
	
	
	
	private static List<TokenizerPattern> patterns;
	
	static {
		patterns = new ArrayList<TokenizerPattern>();
		patterns.add(new TokenizerPattern("^[ \t]+", null));  // Whitespace
		patterns.add(new TokenizerPattern("^[A-Za-z_][A-Za-z0-9_]*:", TokenType.LABEL));
		patterns.add(new TokenizerPattern("^[A-Za-z_][A-Za-z0-9_]*", TokenType.NAME));
		patterns.add(new TokenizerPattern("^%[A-Za-z][A-Za-z0-9_]*", TokenType.REGISTER));
		patterns.add(new TokenizerPattern("^0x[0-9a-fA-F]+", TokenType.HEXADECIMAL));
		patterns.add(new TokenizerPattern("^-?[0-9]+", TokenType.DECIMAL));
		patterns.add(new TokenizerPattern("^\\$", TokenType.DOLLAR));
		patterns.add(new TokenizerPattern("^,", TokenType.COMMA));
		patterns.add(new TokenizerPattern("^\\+", TokenType.PLUS));
		patterns.add(new TokenizerPattern("^-", TokenType.MINUS));
		patterns.add(new TokenizerPattern("^\\(", TokenType.LEFT_PAREN));
		patterns.add(new TokenizerPattern("^\\)", TokenType.RIGHT_PAREN));
		patterns.add(new TokenizerPattern("^[\n\r]+", TokenType.NEWLINE));
		patterns.add(new TokenizerPattern("^#[^\n\r]*", null));  // Comment
		patterns.add(new TokenizerPattern("^$", TokenType.END_OF_FILE));  // Comment
	}
	
	
	public Token nextToken() {
		for (TokenizerPattern pat : patterns) {
			String match = match(pat.pattern);
			if (match != null) {
				offset += match.length();
				if (pat.tokenType != null)
					return new Token(pat.tokenType, match);
			}
		}
		throw new RuntimeException();
	}
	
	
	
	private String match(Pattern pattern) {
		Matcher m = pattern.matcher(sourceCode);
		m.region(offset, sourceCode.length());
		if (!m.find())
			return null;
		else
			return m.group();
	}
	
	
	
	
	private static class TokenizerPattern {
		
		public final Pattern pattern;
		
		public final TokenType tokenType;
		
		
		public TokenizerPattern(String pattern, TokenType tokenType) {
			this.pattern = Pattern.compile(pattern);
			this.tokenType = tokenType;
		}
		
	}
	
}