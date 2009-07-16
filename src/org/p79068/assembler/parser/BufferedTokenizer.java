package org.p79068.assembler.parser;


/**
 * Decorates a tokenizer with peeking capabilities.
 */
final class BufferedTokenizer {
	
	private Tokenizer tokenizer;
	
	private Token nextToken;
	
	
	
	public BufferedTokenizer(Tokenizer tokenizer) {
		if (tokenizer == null)
			throw new NullPointerException();
		this.tokenizer = tokenizer;
		nextToken = null;
	}
	
	
	
	/**
	 * Returns the next token from this tokenizer.
	 * @return the next token from this tokenizer
	 */
	public Token next() {
		if (nextToken != null) {
			Token result = nextToken;
			nextToken = null;
			return result;
		} else
			return tokenizer.next();
	}
	
	
	public Token peek() {
		if (nextToken == null)
			nextToken = tokenizer.next();
		return nextToken;
	}
	
	
	public boolean check(TokenType type) {
		return peek().type == type;
	}
	
}
