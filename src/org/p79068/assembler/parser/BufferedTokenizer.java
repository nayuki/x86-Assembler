package org.p79068.assembler.parser;


final class BufferedTokenizer {
	
	private Tokenizer tokenizer;
	
	private Token nextToken;
	
	
	
	public BufferedTokenizer(Tokenizer tokenizer) {
		if (tokenizer == null)
			throw new NullPointerException();
		this.tokenizer = tokenizer;
		nextToken = null;
	}
	
	
	
	public Token nextToken() {
		if (nextToken != null) {
			Token result = nextToken;
			nextToken = null;
			return result;
		} else
			return tokenizer.nextToken();
	}
	
	
	public Token peek() {
		if (nextToken == null)
			nextToken = tokenizer.nextToken();
		return nextToken;
	}
	
	
	public boolean check(TokenType type) {
		return peek().getType() == type;
	}
	
}